package com.evaneos.evaneostest.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evaneos.evaneostest.R
import com.evaneos.evaneostest.ui.adapters.DestinationDataAdapter
import com.evaneos.evaneostest.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: DestinationDataAdapter
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private lateinit var erreur: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.main_DestinationRV)
        erreur = findViewById(R.id.erreurTv)


    }

    fun retrieveData(mainActivityViewModel: MainActivityViewModel) {
        mMainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        mMainActivityViewModel.getDestinations().observe(this) {
            if (it.isEmpty()) {
                erreur.text = "Oops, something went wrong"
                erreur.visibility = View.VISIBLE
            } else {
                erreur.visibility = View.GONE
                mRecyclerView.visibility = View.VISIBLE
                mAdapter = DestinationDataAdapter(this, it.sortedBy { it.name })
                val linearLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
                mRecyclerView.layoutManager = linearLayoutManager
                mRecyclerView.adapter = mAdapter
            }
        }
    }
}