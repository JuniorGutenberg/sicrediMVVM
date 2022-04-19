package com.orbital.sicredimvvm.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.orbital.core.enums.Status
import com.orbital.sicredimvvm.databinding.FragmentMainBinding
import com.orbital.sicredimvvm.db.model.EventsData
import com.orbital.sicredimvvm.view.adapter.EventsAdapter
import com.orbital.sicredimvvm.viewmodel.MainViewModel
import com.orbital.ui.utils.ViewUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initComponents()
        setObserver()

    }
    private fun initComponents(){
        binding.apply {
            rvEvents.isNestedScrollingEnabled = false
            rvEvents.setHasFixedSize(true)
            rvEvents.layoutManager = LinearLayoutManager(context)
        }
        context?.let { ViewUtils.showLoading(it) }
    }
    private fun setObserver(){
        viewModel.getEvents()
        viewModel.eventList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCESS -> it.data?.let { it1 -> sucess(it1) }
                Status.ERROR -> it.mensage?.let { it1 -> error(it1) }
                Status.ERRORNETWORK -> error("NO CONNECTION")
            }
        }
    }
    private fun sucess(data: List<EventsData>){
        binding.rvEvents.adapter = context?.let { EventsAdapter(data, it) }
        ViewUtils.hideLoading()
    }
    private fun error(msg:String){
        Toast.makeText(context, "Error: $msg",Toast.LENGTH_LONG).show()
        ViewUtils.hideLoading()

    }
}