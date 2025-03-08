package com.ibrahim.helyxonstoreapptask.api

import com.ibrahim.helyxonstoreapptask.model.ProductData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<ArrayList<ProductData>>
}