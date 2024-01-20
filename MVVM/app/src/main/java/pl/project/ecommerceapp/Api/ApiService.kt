package pl.project.ecommerceapp.Api

import pl.project.ecommerceapp.Data.Category
import pl.project.ecommerceapp.Data.LoginRequest
import pl.project.ecommerceapp.Data.LoginResponse
import pl.project.ecommerceapp.Data.Products
import pl.project.ecommerceapp.Data.Adrress
import retrofit2.Call

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("api/category/{id}")
    fun getCategory(@Path("id") id: Int): Call<Category>


    @POST("api/auth/login")
    fun loginUser(@Body loginRequest: LoginRequest):Call<LoginResponse>


    @GET("api/product/list")
    fun getProducts(): Call<Products>

    @GET("api/address/{id}")
     fun getAddress(@Header("Authorization") token: String): Call<Adrress>




}

