package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class EditItemInCartResponse(
    @SerializedName("additionalProp1")
    val additionalProp1: String,
    @SerializedName("additionalProp2")
    val additionalProp2: String,
    @SerializedName("additionalProp3")
    val additionalProp3: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("instance")
    val instance: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
)