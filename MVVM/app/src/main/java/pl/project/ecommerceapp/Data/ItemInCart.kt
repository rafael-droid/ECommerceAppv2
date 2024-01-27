package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class ItemInCart(
    @SerializedName("id")
    val id: Int,
    @SerializedName("product")
    val product: Item,
    @SerializedName("quantity")
    var quantity: Int
)