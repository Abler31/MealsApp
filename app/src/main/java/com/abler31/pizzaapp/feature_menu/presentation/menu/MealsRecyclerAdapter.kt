package com.abler31.pizzaapp.feature_menu.presentation.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abler31.pizzaapp.R
import com.abler31.pizzaapp.feature_menu.data.model.meal.MealsModelEntity
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meal
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meals

class MealsRecyclerAdapter : RecyclerView.Adapter<MealsRecyclerAdapter.ItemsViewHolder>() {
    private var mealsList = emptyList<Meal>()

    inner class ItemsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val mealName = itemView.findViewById<TextView>(R.id.tv_strMeal_item)
        val ingredients = itemView.findViewById<TextView>(R.id.tv_ingredients)

        fun bind(meal: Meal) {
            mealName.text = meal.strMeal
            val ingredientsList = mutableListOf<String>()
            ingredientsList.add(meal.strIngredient1)
            ingredientsList.add(meal.strIngredient2)
            ingredientsList.add(meal.strIngredient3)
            ingredientsList.add(meal.strIngredient4)
            ingredientsList.add(meal.strIngredient5)
            ingredientsList.add(meal.strIngredient6)
            ingredientsList.add(meal.strIngredient7)
            ingredientsList.add(meal.strIngredient8)
            ingredientsList.add(meal.strIngredient9)
            ingredientsList.add(meal.strIngredient10)
            ingredientsList.add(meal.strIngredient11)
            ingredientsList.add(meal.strIngredient12)
            ingredientsList.add(meal.strIngredient13)
            ingredientsList.add(meal.strIngredient14)
            ingredientsList.add(meal.strIngredient15)
            ingredientsList.add(meal.strIngredient16)
            ingredientsList.add(meal.strIngredient17)
            ingredientsList.add(meal.strIngredient18)
            ingredientsList.add(meal.strIngredient19)
            ingredientsList.add(meal.strIngredient20)
            val nonEmptyStrings = ingredientsList.filter { it.isNotEmpty() }
            val ingredientsStr = nonEmptyStrings.joinToString(", ")
            ingredients.text = ingredientsStr
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_meal, parent, false)
        return ItemsViewHolder(
            itemView
        )
    }

    override fun getItemCount() = mealsList.size

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(mealsList[position])
    }

    fun setData(data: Meals) {
        val newList = mutableListOf<Meal>()
        data.mealEntities.forEach {
            newList.add(it)
        }
        mealsList = newList
        notifyDataSetChanged()
    }
}
