package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class EditCartItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("quantity")
    val quantity: Int
)