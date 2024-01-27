package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class ImageUrl(
    @SerializedName("id")
    val id: Int,
    @SerializedName("url")
    val url: String
)