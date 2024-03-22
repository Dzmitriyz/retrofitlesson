package com.example.retrofitlesson.retrofit

import retrofit2.http.GET

interface ProductAPI {
    @GET("products/1")
    suspend fun getProductById():Product
}