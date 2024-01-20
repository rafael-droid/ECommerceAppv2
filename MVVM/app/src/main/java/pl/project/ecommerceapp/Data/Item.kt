package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("category")
    val category: Category,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
)