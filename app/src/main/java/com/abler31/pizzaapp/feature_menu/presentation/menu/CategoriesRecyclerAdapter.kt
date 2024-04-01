package com.abler31.pizzaapp.feature_menu.presentation.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.abler31.pizzaapp.R
import com.abler31.pizzaapp.feature_menu.domain.model.category.Categories
import com.abler31.pizzaapp.feature_menu.domain.model.category.Category

class CategoriesRecyclerAdapter: RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoriesViewHolder>() {

    private var itemSelectedListener: ((category: Category) -> Unit)? = null
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

    var selectedPosition = -1
    var lastSelectedPosition = -1
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categoriesList[position])
        holder.itemView.setOnClickListener {
            lastSelectedPosition = selectedPosition;
            selectedPosition = holder.adapterPosition;
            notifyItemChanged(lastSelectedPosition);
            notifyItemChanged(selectedPosition);
            itemSelectedListener?.invoke(categoriesList[holder.adapterPosition])
        }

        val selectedColorBackground = ContextCompat.getColor(holder.itemView.context, R.color.red_category_background)
        val unselectedColorBackground = ContextCompat.getColor(holder.itemView.context, R.color.white)
        val selectedColorText = ContextCompat.getColor(holder.itemView.context, R.color.red)
        val unselectedColorText = ContextCompat.getColor(holder.itemView.context, R.color.grey_text_category)
        if (selectedPosition == holder.adapterPosition) {
            holder.itemView.findViewById<TextView>(R.id.tv_category_item).setTextColor(selectedColorText)
            holder.itemView.findViewById<TextView>(R.id.tv_category_item).setBackgroundColor(selectedColorBackground)
        } else {
            holder.itemView.findViewById<TextView>(R.id.tv_category_item).setTextColor(unselectedColorText)
            holder.itemView.findViewById<TextView>(R.id.tv_category_item).setBackgroundColor(unselectedColorBackground)
        }

    }

    fun setData(categories: Categories) {
        val newList = mutableListOf<Category>()
        categories.categories.forEach {
            newList.add(it)
        }
        categoriesList = newList
        notifyDataSetChanged()
    }

    fun setItemSelectedListener(listener: ((category: Category) -> Unit)?) {
        this.itemSelectedListener = listener
    }
}