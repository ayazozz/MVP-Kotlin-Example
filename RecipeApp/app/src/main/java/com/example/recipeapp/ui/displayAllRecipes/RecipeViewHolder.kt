package com.example.recipeapp.ui.displayAllRecipes

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipeapp.R
import com.example.recipeapp.data.model.RecipeDataModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_row_layout.view.*

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("NewApi")
    fun bind(recipe: RecipeDataModel?, listener: RecipesAdapter.Listener) {

        if (recipe != null) {

            Picasso.get().load("https:" + recipe.recipePhotoUrl)
                .resize(960, 360)
                .into(itemView.recipe_img)
            itemView.recipe_title.text = recipe.recipeTitle
            itemView.setOnClickListener { listener.onItemClick(recipe) }

        }

    }

    companion object {
        fun create(parent: ViewGroup): RecipeViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recipe_row_layout, parent, false)
            return RecipeViewHolder(view)
        }
    }
}