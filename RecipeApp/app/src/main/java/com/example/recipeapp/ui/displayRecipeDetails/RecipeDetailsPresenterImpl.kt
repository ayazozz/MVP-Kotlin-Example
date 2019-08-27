package com.example.recipeapp.ui.displayRecipeDetails

import android.util.Log
import com.example.recipeapp.data.model.RecipeDetailModel
import com.example.recipeapp.data.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RecipeDetailsPresenterImpl(val detailView: RecipeDetailView?) : RecipeDetailsPresenter {

    private val apiCookie = ApiService.getService()
    private var compositeDisposable = CompositeDisposable()

    override fun getDetails(recipeId: String) {

        compositeDisposable.add(
            apiCookie.getRecipeDetails(detailView!!.getAccessToken(), recipeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        )
    }

    private fun handleResponse(recipe: RecipeDetailModel) {

        val recipeTags: MutableList<String>? = ArrayList()
        var chefName = " -- "

        @Suppress("SENSELESS_COMPARISON")
        if (recipe.includes.entry != null) {
            for (i in 0..recipe.includes.entry.size - 1) {

                if (recipe.includes.entry[i].entrySys.contentType.contentTypeSys.id.equals("chef"))
                    chefName = recipe.includes.entry[i].entryField.name
                else
                    recipeTags!!.add(recipe.includes.entry[i].entryField.name) // add tags
            }

        }

        detailView!!.bindDetailInfo(recipeTags!!, chefName)
    }


    private fun handleError(error: Throwable) {
        Log.d("type", error.localizedMessage)
        detailView!!.displayMessage(error.localizedMessage)
    }

    interface RecipeDetailView {
        fun getAccessToken(): String
        fun displayMessage(message: String)
        fun bindDetailInfo(recipeTags: MutableList<String>, recipeChefName: String)
    }
}