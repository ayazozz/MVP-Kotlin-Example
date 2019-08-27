package com.example.recipeapp.ui.displayAllRecipes

import android.util.Log
import com.example.recipeapp.data.model.RecipeDataModel
import com.example.recipeapp.data.model.RecipeModel
import com.example.recipeapp.data.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DisplayRecipesPresenterImpl(val displayView: DisplayRecipesView?) : DisplayRecipesPresenter {

    private val apiCookie = ApiService.getService()
    private var compositeDisposable = CompositeDisposable()

    override fun getRecipes() {

        compositeDisposable.add(
            apiCookie.getRecipes(displayView!!.getAccessToken(), "recipe")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        )
    }


    private fun handleResponse(recipes: RecipeModel) {

        val recipeList: MutableList<RecipeDataModel> = ArrayList()

        for (j in 0..recipes.items.size - 1) {

            val photoId = recipes.items[j].itemFields.photo.photoSys.photoId

            for (i in 0 until recipes.includes.asset.size) {
                if (photoId == recipes.includes.asset[i].assetSys.assertId) {
                    val photoUrl = recipes.includes.asset[i].assetFields.file.url
                    val recipeDataModel = RecipeDataModel(
                        recipes.items[j].itemSys.recipeId,
                        recipes.items[j].itemFields.title, photoUrl, recipes.items[j].itemFields.description
                    )
                    recipeList.add(recipeDataModel)
                    break
                }

            }

        }

        displayView!!.managePrgressState(true)
        displayView.displayRecipes(recipeList)

    }

    private fun handleError(error: Throwable) {
        Log.d("type", error.localizedMessage)
        displayView!!.managePrgressState(false)
        displayView.displayMessage(error.localizedMessage)
    }


    interface DisplayRecipesView {

        fun displayMessage(message: String)
        fun displayRecipes(recipeLis: MutableList<RecipeDataModel>)
        fun getAccessToken(): String
        fun managePrgressState(state: Boolean)

    }

}