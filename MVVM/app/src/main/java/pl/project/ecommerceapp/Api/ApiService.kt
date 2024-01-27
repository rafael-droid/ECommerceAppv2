package pl.project.ecommerceapp.Api

import pl.project.ecommerceapp.Data.Address
import pl.project.ecommerceapp.Data.Cart
import pl.project.ecommerceapp.Data.CartListResponse
import pl.project.ecommerceapp.Data.CartResponse
import pl.project.ecommerceapp.Data.Category
import pl.project.ecommerceapp.Data.CouponResponse
import pl.project.ecommerceapp.Data.EditCartItem
import pl.project.ecommerceapp.Data.EditItemInCartResponse
import pl.project.ecommerceapp.Data.LoginRequest
import pl.project.ecommerceapp.Data.LoginResponse
import pl.project.ecommerceapp.Data.Products
import pl.project.ecommerceapp.Data.ItemDetail
import pl.project.ecommerceapp.Data.ItemInCart
import pl.project.ecommerceapp.Data.RegisterRequest
import pl.project.ecommerceapp.Data.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("api/category/{id}")
    fun getCategory(@Path("id") id: Int): Call<Category>

    @POST("api/auth/login")
    fun loginUser(@Body loginRequest: LoginRequest):Call<LoginResponse>

    @POST("api/auth/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>

    @GET("api/product/list")
    fun getProducts(): Call<Products>

    @GET("api/address/list")
     fun getAddress(@Header("Authorization") token: String): Call<Address>

     @GET("api/product/{id}")
     fun getProductById(@Path("id") id: Int): Call<ItemDetail>

    @POST("api/cart-item")
    fun addProduct(@Body cart: Cart, @Header("Authorization") token: String):Call<CartResponse>

    @GET("api/cart-item/list")
    fun getCart(@Header("Authorization") token: String):Call<CartListResponse>

    @GET("api/coupon/check/{code}")
    fun checkCoupon(@Header("Authorization") token: String, @Path("code") code: String):Call<CouponResponse>

    @PUT("api/cart-item/{id}")
    fun changeQuantity(@Header("Authorization") token: String, @Path("id") id: String, @Body editItemInCart: EditCartItem): Call<EditItemInCartResponse>


}

