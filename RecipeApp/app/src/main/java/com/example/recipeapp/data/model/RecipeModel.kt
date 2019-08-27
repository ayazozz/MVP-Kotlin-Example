package com.example.recipeapp.data.model

import com.google.gson.annotations.SerializedName

data class RecipeModel(

    @SerializedName("items") var items: List<Item>,
    @SerializedName("includes") var includes: Includes
)

data class Item(
    @SerializedName("sys") var itemSys: ItemSys,
    @SerializedName("fields") var itemFields: ItemFields

)

data class Includes(
    @SerializedName("Entry") var entries: List<Entry>,
    @SerializedName("Asset") var asset: List<Asset>
)

data class Asset(

    @SerializedName("sys") var assetSys: AssetSys,
    @SerializedName("fields") var assetFields: AssetFields
)

data class Chef(
    @SerializedName("sys") var chefSys: ChefSys

)

data class Entry(
    @SerializedName("sys") var entrySys: EntrySys,
    @SerializedName("fields") var entryField: EntryFields
)

data class ItemFields(
    @SerializedName("title") var title: String,
    @SerializedName("photo") var photo: Photo,
    @SerializedName("description") var description: String,
    @SerializedName("chef") var chef: Chef,
    @SerializedName("tags") var tags: List<Tag>
)

data class EntryFields(
    @SerializedName("name") val fieldName: String
)

data class AssetFields(

    @SerializedName("title") var title: String,
    @SerializedName("file") var file: File
)

data class File(
    @SerializedName("url") var url: String
)

data class Photo(
    @SerializedName("sys") var photoSys: PhotoSys
)

data class Tag(
    @SerializedName("sys") var tagSys: TagSys
)

data class ItemSys(
    @SerializedName("id") var recipeId: String

)

data class AssetSys(
    @SerializedName("id") var assertId: String
)

data class EntrySys(
    @SerializedName("id") var entryId: String
)

data class TagSys(
    @SerializedName("id") var tagId: String
)

data class ChefSys(
    @SerializedName("id") var chefId: String
)

data class PhotoSys(
    @SerializedName("id") var photoId: String
)