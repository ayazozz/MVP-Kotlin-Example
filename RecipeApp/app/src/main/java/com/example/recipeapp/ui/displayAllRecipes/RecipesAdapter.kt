package com.example.recipeapp.ui.displayAllRecipes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.recipeapp.R
import com.example.recipeapp.data.model.RecipeDataModel

class RecipesAdapter(val recipes: MutableList<RecipeDataModel>, private val listener: DisplayRecipesActivity) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    interface Listener {
        fun onItemClick(recipe: RecipeDataModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recipe_row_layout, parent, false)
        return RecipeViewHolder(v)

    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position], listener)

    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}