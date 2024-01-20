package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("url")
    val url: Any
)