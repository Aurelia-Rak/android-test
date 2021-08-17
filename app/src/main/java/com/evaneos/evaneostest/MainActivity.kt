package com.evaneos.evaneostest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evaneos.evaneostest.adapters.DestinationDataAdapter
import com.evaneos.evaneostest.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: DestinationDataAdapter
    private lateinit var mMainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mRecyclerView = findViewById(R.id.main_DestinationRV)

        mMainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

       mMainActivityViewModel.getDestinations().observe(this){
           mAdapter = DestinationDataAdapter(this, it.sortedBy { it.name })
           val linearLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
           mRecyclerView.layoutManager = linearLayoutManager
           mRecyclerView.adapter = mAdapter
       }
    }
}