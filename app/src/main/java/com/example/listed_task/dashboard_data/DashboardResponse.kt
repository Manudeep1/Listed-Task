package com.example.listed_task.dashboard_data

data class DashboardResponse(
    val applied_campaign: Int,
    val data: Data,
    val extra_income: Float,
    val links_created_today: Int,
    val message: String,
    val startTime: String,
    val status: Boolean,
    val statusCode: Int,
    val support_whatsapp_number: String,
    val today_clicks: Int,
    val top_location: String,
    val top_source: String,
    val total_clicks: Int,
    val total_links: Int
)