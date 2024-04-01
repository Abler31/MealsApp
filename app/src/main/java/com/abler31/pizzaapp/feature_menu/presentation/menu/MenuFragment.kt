package com.abler31.pizzaapp.feature_menu.presentation.menu

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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

        //categories
        categoriesAdapter = CategoriesRecyclerAdapter()
        categoriesRecyclerView = view.findViewById(R.id.rv_categories)
        categoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoriesRecyclerView.adapter = categoriesAdapter
        vm.categories.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    categoriesAdapter.setData(it.data!!)
                }
                is Resource.Error -> {
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

        vm.meals.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    mealsAdapter.setData(it.data!!)
                    Log.d("test", "resource success в observe ${ it.data!!.mealEntities[0].strMeal }")
                }
                is Resource.Error -> {
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