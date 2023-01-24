package com.hamy.compose.unitTesting.data.remote

import com.hamy.compose.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixBayApi {

    @GET("/api/")
   suspend fun searchForImage(
        @Query("q") searchQuery:String,
        @Query("key") apiKey : String = BuildConfig.API_KEY
   ): Response<ImageResponse>

}