package com.example.listed_task

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class DayAxisValueFormatter : ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        // Assuming value represents the day of the month (1-31)
        val dayOfMonth = value.toInt()
        return dayOfMonth.toString()
    }
}