package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class CouponResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("isValid")
    val isValid: Boolean,
    @SerializedName("percentage")
    val percentage: Int
)