package com.example.recipeapp.ui.displayAllRecipes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.recipeapp.R
import com.example.recipeapp.data.model.RecipeDataModel
import com.example.recipeapp.ui.displayRecipeDetails.RecipeDetailFragment

class DisplayRecipesActivity : AppCompatActivity(), RecipesAdapter.Listener,
    DisplayRecipesPresenterImpl.DisplayRecipesView {

    // val recipeList: MutableList<RecipeDataModel> = ArrayList()
    lateinit var presenter: DisplayRecipesPresenter
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorMessage: TextView
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_recipes)

        initialization()
        presenter.getRecipes()
    }

    private fun initialization() {
        recyclerView = findViewById(R.id.recipe_recycler_view)
        errorMessage = findViewById(R.id.txt_error)
        progressBar = findViewById(R.id.progress_bar)
        presenter = DisplayRecipesPresenterImpl(this)

        val actionBar = supportActionBar
        actionBar!!.title = "Recipes"
    }


    override fun getAccessToken(): String {
        return resources.getString(R.string.access_token)
    }

    override fun displayRecipes(recipeLis: MutableList<RecipeDataModel>) {

        recipesAdapter = RecipesAdapter(recipeLis, this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView.adapter = recipesAdapter

    }

    override fun displayMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun managePrgressState(state: Boolean) {

        if (!state)
            errorMessage.visibility = View.VISIBLE
        else
            progressBar.visibility = View.GONE

    }

    override fun onItemClick(recipe: RecipeDataModel) {

        val detailFragment = RecipeDetailFragment.newInstance(recipe)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.recipe_list_screen, detailFragment, "detail_fragment")
            .addToBackStack(null).commit()

    }


}
