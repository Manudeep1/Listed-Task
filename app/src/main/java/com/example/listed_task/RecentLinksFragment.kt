package com.example.listed_task

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listed_task.api_interface.ApiServices
import com.example.listed_task.recycler_view.RecentLinksAdapter
import com.example.listed_task.repository.Repository
import com.example.listed_task.view_models.MainViewFactory
import com.example.listed_task.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecentLinksFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val retrofitService  = ApiServices.getInstance()
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_recent_links, container, false)

        viewModel = ViewModelProvider(this, MainViewFactory(Repository(retrofitService))).get(MainViewModel::class.java)
        val rcv = view.findViewById<RecyclerView>(R.id.rv_recent_links)
        val rcvForTopLinks = view.findViewById<RecyclerView>(R.id.rv_top_links)
        viewModel.userData.observe(viewLifecycleOwner){
            rcv.layoutManager = LinearLayoutManager(context)
            rcv.adapter = RecentLinksAdapter(this,it.data.recent_links)
        }
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getDashboardData()
        }
        return view
    }

}