package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class UserAddress(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String
)