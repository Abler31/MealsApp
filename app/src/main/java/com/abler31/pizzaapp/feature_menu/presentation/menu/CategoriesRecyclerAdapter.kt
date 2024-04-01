package com.abler31.pizzaapp.feature_menu.presentation.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abler31.pizzaapp.R
import com.abler31.pizzaapp.feature_menu.domain.model.category.Categories
import com.abler31.pizzaapp.feature_menu.domain.model.category.Category

class CategoriesRecyclerAdapter: RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoriesViewHolder>() {

    private var categoriesList = emptyList<Category>()

    inner class CategoriesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val categoryName = itemView.findViewById<TextView>(R.id.tv_category_item)

        fun bind(category: Category) {
            categoryName.text = category.strCategory
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_category, parent, false)
        return CategoriesViewHolder(
            itemView
        )
    }

    override fun getItemCount() = categoriesList.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categoriesList[position])
    }

    fun setData(categories: Categories) {
        val newList = mutableListOf<Category>()
        categories.categories.forEach {
            newList.add(it)
        }
        categoriesList = newList
        notifyDataSetChanged()
    }



}