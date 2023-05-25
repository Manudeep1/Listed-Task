package com.example.listed_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listed_task.api_interface.ApiServices
import com.example.listed_task.recycler_view.TopLinksAdapter
import com.example.listed_task.repository.Repository
import com.example.listed_task.view_models.MainViewFactory
import com.example.listed_task.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TopLinksFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private val retrofitService  = ApiServices.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_top_links, container, false)
        viewModel = ViewModelProvider(this, MainViewFactory(Repository(retrofitService))).get(MainViewModel::class.java)
        val rcvForTopLinks = view.findViewById<RecyclerView>(R.id.rv_top_links)
        viewModel.userData.observe(viewLifecycleOwner){
            rcvForTopLinks.layoutManager = LinearLayoutManager(context)
            rcvForTopLinks.adapter = TopLinksAdapter(this,it.data.top_links)
        }
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getDashboardData()
        }
        return view
    }


}