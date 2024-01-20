package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("totalRows")
    val totalRows: Int
)