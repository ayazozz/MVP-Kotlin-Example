package com.example.recipeapp.data.model

import com.google.gson.annotations.SerializedName

data class RecipeDetailModel(
    @SerializedName("includes") var includes: DetailIncludes
)

data class DetailIncludes(
    @SerializedName("Entry") var entry: List<DetailEntry>
)

data class ContentType(
    @SerializedName("sys") var contentTypeSys: ContentTypeSys
)

data class ContentTypeSys(
    @SerializedName("id") var id: String
)

data class DetailEntry(

    @SerializedName("sys") var entrySys: DetailEntriySys,
    @SerializedName("fields") var entryField: Fields
)

data class DetailEntriySys(
    @SerializedName("contentType") var contentType: ContentType
)

data class Fields(
    @SerializedName("name") var name: String
)