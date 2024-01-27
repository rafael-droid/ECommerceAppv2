package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class ItemAddress(
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("region")
    val region: String,
    @SerializedName("streetAddress")
    val streetAddress: String,
    @SerializedName("user")
    val user: UserAddress,
    @SerializedName("zipCode")
    val zipCode: String
)