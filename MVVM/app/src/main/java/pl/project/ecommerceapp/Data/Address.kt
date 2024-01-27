package pl.project.ecommerceapp.Data


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("items")
    val items: List<ItemAddress>,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("totalRows")
    val totalRows: Int
)