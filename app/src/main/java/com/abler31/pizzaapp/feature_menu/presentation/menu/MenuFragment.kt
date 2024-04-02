package com.abler31.pizzaapp.feature_menu.presentation.menu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abler31.pizzaapp.R
import com.abler31.pizzaapp.databinding.FragmentHomeBinding
import com.abler31.pizzaapp.feature_menu.domain.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    val vm by viewModels<MenuViewModel>()
    private lateinit var mealsAdapter: MealsRecyclerAdapter
    lateinit var mealsRecyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter
    lateinit var categoriesRecyclerView: RecyclerView
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()
        val dataStoredPreferences = context?.getSharedPreferences("dataStored", Context.MODE_PRIVATE)


        //categories
        categoriesAdapter = CategoriesRecyclerAdapter()
        categoriesRecyclerView = view.findViewById(R.id.rv_categories)
        categoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoriesRecyclerView.adapter = categoriesAdapter
        val isCategoryDataStored =
            dataStoredPreferences?.getBoolean("isCategoryDataStored", false)
        vm.categories.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    categoriesAdapter.setData(it.data!!)
                    if (!isCategoryDataStored!!){
                        it.data.categories.forEach {category ->
                            vm.insertCategoryData(category)
                        }
                        with (dataStoredPreferences.edit()) {
                            putBoolean("isCategoryDataStored", true)
                            apply()
                        }
                    }
                }
                is Resource.Error -> {
                    if (isCategoryDataStored == true){
                        vm.getCategoryData()
                    }
                    Log.d("test", it.message.toString())
                }
                is Resource.Loading -> {

                }
            }
        }

        categoriesAdapter.setItemSelectedListener {
            Log.d("test", "${it.strCategory}")

            vm.getMealsByCategory(it)
        }
        //meals
        mealsAdapter = MealsRecyclerAdapter()
        mealsRecyclerView = view.findViewById(R.id.rv_meals)
        mealsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mealsRecyclerView.adapter = mealsAdapter
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        val isMealsDataStored =
            dataStoredPreferences?.getBoolean("isMealDataStored", false) //first time meals data stored flag
        Log.d("test", "$isMealsDataStored")
        vm.meals.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    Log.d("test", "отработал meal observe")
                    progressBar.visibility = View.GONE
                    mealsAdapter.setData(it.data!!)
                    if (!isMealsDataStored!!){
                        it.data.mealEntities.forEach {mealEntity ->
                            vm.insertMealData(mealEntity)
                        }
                        with (dataStoredPreferences.edit()) {
                            putBoolean("isMealDataStored", true)
                            apply()
                        }
                    }

                    Log.d("test", "resource success в observe ${ it.data!!.mealEntities[0].strMeal }")
                }
                is Resource.Error -> {
                    if (isMealsDataStored == true){
                        vm.getMealsData()
                    }
                    Log.d("test", it.message.toString())
                }
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }

        vm.mealsByCategory.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    mealsAdapter.setData(it.data!!)
                }
                is Resource.Error -> {
                    Log.d("test", it.message.toString())
                }
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
        vm.getMeals()
        vm.getCategories()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}