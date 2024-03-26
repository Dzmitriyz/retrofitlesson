package com.example.retrofitlesson.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MainAPI {
    @GET("auth/products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product


    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest):Response<Users>

    @Headers("Content-Type: application/json")
    @GET("auth/product")
    suspend fun getAllProducts(@Header("Authorization") token: String): Products

    @Headers("Content-Type: application/json")
    @GET("auth/product/search")
    suspend fun getProductByNameAuth(@Header("Authorization") token: String, @Query ("q") name :String ): Products
}