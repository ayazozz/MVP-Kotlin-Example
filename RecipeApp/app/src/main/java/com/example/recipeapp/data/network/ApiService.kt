package com.example.recipeapp.data.network

import com.example.recipeapp.data.model.RecipeDetailModel
import com.example.recipeapp.data.model.RecipeModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //get all recipes
    @GET("/spaces/kk2bw5ojx476/entries")
    fun getRecipes(@Query("access_token") accessToken: String, @Query("content_type") content_type: String): Observable<RecipeModel>

    //get recipe details
    @GET("/spaces/kk2bw5ojx476/entries")
    fun getRecipeDetails(@Query("access_token") accessToken: String, @Query("sys.id") recipeId: String): Observable<RecipeDetailModel>

    companion object {
        fun getService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://cdn.contentful.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }


}