package pl.project.ecommerceapp.Data

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("token")
    val token: String
)