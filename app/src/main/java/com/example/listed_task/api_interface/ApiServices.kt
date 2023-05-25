package com.example.listed_task.api_interface

import com.example.listed_task.Utils
import com.example.listed_task.dashboard_data.DashboardResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiServices {
    @GET("dashboardNew")
    suspend fun getDashboardData(@Header("Authorization") token: String): DashboardResponse


    companion object{
        var retrofitService: ApiServices? = null
        fun getInstance(): ApiServices{
            if (retrofitService == null)
            {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Utils.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                retrofitService = retrofit.create(ApiServices::class.java)
            }
            return retrofitService!!
        }
    }
}