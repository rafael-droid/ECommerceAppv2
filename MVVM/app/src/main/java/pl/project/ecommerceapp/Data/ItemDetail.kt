package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class ItemDetail(
    @SerializedName("category")
    val category: Category,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrls")
    val imageUrls: List<ImageUrl>,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("stock")
    val stock: Int
)