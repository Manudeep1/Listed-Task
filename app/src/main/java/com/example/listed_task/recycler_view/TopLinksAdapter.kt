package com.example.listed_task.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.listed_task.R
import com.example.listed_task.TopLinksFragment
import com.example.listed_task.dashboard_data.TopLink

class TopLinksAdapter (private val context: TopLinksFragment, private val topLinksList : List<TopLink>):
    RecyclerView.Adapter<TopLinksAdapter.TopLinksViewHolder>() {
    class TopLinksViewHolder(ItemView : View): RecyclerView.ViewHolder(ItemView){
        val iv_icon = ItemView.findViewById<ImageView>(R.id.iv_icon)
        val tv_linkName = ItemView.findViewById<TextView>(R.id.tv_link_name)
        val tv_date = ItemView.findViewById<TextView>(R.id.tv_date)
        val tv_clicks = ItemView.findViewById<TextView>(R.id.tv_clicks)
        val tv_links = ItemView.findViewById<TextView>(R.id.tv_links)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopLinksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recents_links_item_view,parent,false)
        return TopLinksViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topLinksList.size
    }

    override fun onBindViewHolder(holder: TopLinksViewHolder, position: Int) {
        holder.apply {
            tv_date.text = topLinksList[position].created_at
            tv_linkName.text = topLinksList[position].title
            tv_links.text = topLinksList[position].smart_link
            tv_clicks.text = topLinksList[position].total_clicks.toString()
        }
        Glide.with(context).load(topLinksList[position].original_image).apply(
            RequestOptions().transform(
            RoundedCorners(18))).fitCenter().into(holder.iv_icon)
    }

}