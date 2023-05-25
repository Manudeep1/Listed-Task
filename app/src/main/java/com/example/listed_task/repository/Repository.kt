package com.example.listed_task.repository

import com.example.listed_task.Utils
import com.example.listed_task.api_interface.ApiServices

class Repository constructor(private val apiService: ApiServices) {
    suspend fun getData() = apiService.getDashboardData(Utils.TOKEN)
}