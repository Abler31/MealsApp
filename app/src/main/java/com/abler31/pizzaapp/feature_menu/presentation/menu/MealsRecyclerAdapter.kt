package com.abler31.pizzaapp.feature_menu.presentation.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abler31.pizzaapp.R
import com.abler31.pizzaapp.feature_menu.data.model.network.meal.MealEntity
import com.abler31.pizzaapp.feature_menu.data.model.network.meal.MealsModelEntity
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meal
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meals
import com.bumptech.glide.Glide

class MealsRecyclerAdapter : RecyclerView.Adapter<MealsRecyclerAdapter.ItemsViewHolder>() {
    private var mealsList = emptyList<MealEntity>()

    inner class ItemsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val mealName = itemView.findViewById<TextView>(R.id.tv_strMeal_item)
        val ingredients = itemView.findViewById<TextView>(R.id.tv_ingredients)
        val img = itemView.findViewById<ImageView>(R.id.img_meal_item)

        fun bind(meal: MealEntity) {
            mealName.text = meal.strMeal
            val ingredientsList = mutableListOf<String?>()
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
            val nonEmptyStrings = ingredientsList.filter { it?.isNotEmpty() ?: false }
            val ingredientsStr = nonEmptyStrings.joinToString(", ")
            ingredients.text = ingredientsStr

            Glide.with(itemView.context)
                .load(meal.strMealThumb)
                .placeholder(R.drawable.menu_icon)
                .error(R.drawable.profile_icon)
                .into(img)

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

    fun setData(data: MealsModelEntity) {
        val newList = mutableListOf<MealEntity>()
        data.mealEntities.forEach {
            newList.add(it)
        }
        mealsList = newList
        notifyDataSetChanged()
    }
}

