package com.example.listed_task

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.listed_task.api_interface.ApiServices
import com.example.listed_task.dashboard_data.DashboardResponse
import com.example.listed_task.dashboard_data.Token
import com.example.listed_task.database.TokenDatabase
import com.example.listed_task.databinding.ActivityMainBinding
import com.example.listed_task.repository.Repository
import com.example.listed_task.view_models.MainViewFactory
import com.example.listed_task.view_models.MainViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var data : DashboardResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService : ApiServices = ApiServices.getInstance()
        val database = TokenDatabase.getDataBase(this)
        val token = Token(1,Utils.TOKEN)
        CoroutineScope(Dispatchers.IO).launch {
            database.tokenDao().insertToken(token)
            //val token = database.tokenDao().getToken()
            Utils.TOKEN = token.token
        }

        viewModel = ViewModelProvider(this, MainViewFactory(Repository(retrofitService))).get(MainViewModel::class.java)
        viewModel.userData.observe(this, Observer {
            Log.d("MainActi", "DB: $it")
            data = it
            binding.apply {
                tvTodayClicks.text = it.today_clicks.toString()
                tvLocation.text = it.top_location
                tvSource.text = it.top_source
            }
        })
        val calendar = Calendar.getInstance()
        val timeZone = TimeZone.getDefault()
        calendar.timeZone = timeZone

        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        if(currentHour in 5..11)
        {
            binding.tvGreating.text = "Good Morning"
        }
        else if(currentHour in 12..16)
        {
            binding.tvGreating.text = "Good Afternoon"
        }
        else if(currentHour in 17..19)
        {
            binding.tvGreating.text = "Good Evening"
        }
        else{
            binding.tvGreating.text = "Good Night"
        }
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getDashboardData()
        }
        val entries = mutableListOf<Entry>()

        val overallUrlChart = mapOf(
            "2023-04-25" to 0,
            "2023-04-26" to 1,
            "2023-04-27" to 0,
            "2023-04-28" to 5,
            "2023-04-29" to 0,
            "2023-04-30" to 0,
            "2023-05-01" to 0,
            "2023-05-02" to 0,
            "2023-05-03" to 0,
            "2023-05-04" to 0,
            "2023-05-05" to 0,
            "2023-05-06" to 0,
            "2023-05-07" to 0,
            "2023-05-08" to 0,
            "2023-05-09" to 0,
            "2023-05-10" to 0,
            "2023-05-11" to 0,
            "2023-05-12" to 1,
            "2023-05-13" to 1,
            "2023-05-14" to 0,
            "2023-05-15" to 0,
            "2023-05-16" to 0,
            "2023-05-17" to 0,
            "2023-05-18" to 0,
            "2023-05-19" to 9,
            "2023-05-20" to 4,
            "2023-05-21" to 1,
            "2023-05-22" to 10,
            "2023-05-23" to 7,
            "2023-05-24" to 9,
            "2023-05-25" to 0
        )

        for ((date, value) in overallUrlChart) {
            val dateParts = date.split("-")
            val xValue = dateParts[2].toFloat()
            val yValue = value.toFloat()
            entries.add(Entry(xValue, yValue))
        }

        binding.apply {
            lineChart.setDrawGridBackground(false)
            lineChart.description.isEnabled = false
            lineChart.legend.isEnabled = false
            val xAxis = binding.lineChart.xAxis
            xAxis.valueFormatter = DayAxisValueFormatter()
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f


            val dataSet = LineDataSet(entries, "Data")


            dataSet.color = Color.RED
            val lineData = LineData(dataSet)
            lineChart.data = lineData


            lineChart.invalidate()
        }

        val frag = findViewById<View>(R.id.fragment_container)
        binding.NavigationView.setupWithNavController(frag.findNavController())
    }
}