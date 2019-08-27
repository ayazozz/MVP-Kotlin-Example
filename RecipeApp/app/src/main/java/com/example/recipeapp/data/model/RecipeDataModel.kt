package com.example.recipeapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeDataModel(

    var recipeId: String,
    var recipeTitle: String,
    var recipePhotoUrl: String,
    var recipeDescription: String

) : Parcelable