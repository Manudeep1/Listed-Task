package com.example.listed_task.recycler_view

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.listed_task.R
import com.example.listed_task.RecentLinksFragment
import com.example.listed_task.dashboard_data.RecentLink
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class RecentLinksAdapter(private val context: RecentLinksFragment ,private val recentLinksList : List<RecentLink>):RecyclerView.Adapter<RecentLinksAdapter.RecentLinksViewHolder>() {
    class RecentLinksViewHolder(ItemView : View): RecyclerView.ViewHolder(ItemView){
        val iv_icon = ItemView.findViewById<ImageView>(R.id.iv_icon)
        val tv_linkName = ItemView.findViewById<TextView>(R.id.tv_link_name)
        val tv_date = ItemView.findViewById<TextView>(R.id.tv_date)
        val tv_clicks = ItemView.findViewById<TextView>(R.id.tv_clicks)
        val tv_links = ItemView.findViewById<TextView>(R.id.tv_links)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentLinksAdapter.RecentLinksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recents_links_item_view,parent,false)
        return RecentLinksViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentLinksAdapter.RecentLinksViewHolder, position: Int) {
        holder.tv_clicks.text = recentLinksList[position].total_clicks.toString()

        val timestamp = "2023-02-23T11:45:54.000Z"
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val date = inputFormat.parse(timestamp)

        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = outputFormat.format(date)

        holder.apply {
            tv_date.text = formattedDate
            tv_linkName.text = recentLinksList[position].title
            tv_links.text = recentLinksList[position].smart_link

        }
        Glide.with(context).load(recentLinksList[position].original_image).apply(RequestOptions().transform(
            RoundedCorners(8)
        )).centerCrop().into(holder.iv_icon)
    }

    override fun getItemCount(): Int {
        return recentLinksList.size
    }
}