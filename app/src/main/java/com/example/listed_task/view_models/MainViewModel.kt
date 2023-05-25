package com.example.listed_task.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listed_task.dashboard_data.DashboardResponse
import com.example.listed_task.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _userData: MutableLiveData<DashboardResponse> = MutableLiveData()
    val userData: LiveData<DashboardResponse> = _userData



    suspend fun getDashboardData()
    {
        val response = repository.getData()
        Log.d("Data recived"," ${response}")
        /*response.enqueue(object : Callback<DashboardResponse?> {
            override fun onResponse(
                call: Call<DashboardResponse?>,
                response: Response<DashboardResponse?>
            ) {
                if (response.isSuccessful){
                    val responseData = response.body()!!
                    Log.d("My Response","Data : ${responseData}")
                    dashboardLiveData.postValue(responseData)
                }
            }

            override fun onFailure(call: Call<DashboardResponse?>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })*/
        if(response.statusCode == 200)
        {
            _userData.postValue(response)
        }

    }
}