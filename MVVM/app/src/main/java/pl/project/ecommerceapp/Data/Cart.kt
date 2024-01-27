package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("productId")
    val productId: String,
    @SerializedName("quantity")
    val quantity: Int
)