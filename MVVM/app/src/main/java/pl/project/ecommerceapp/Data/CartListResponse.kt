package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class CartListResponse(
    @SerializedName("items")
    val items: List<ItemInCart>,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("totalRows")
    val totalRows: Int
)