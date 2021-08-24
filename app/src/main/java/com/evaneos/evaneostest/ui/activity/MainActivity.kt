package com.evaneos.evaneostest.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evaneos.data.model.Destination
import com.evaneos.evaneostest.R
import com.evaneos.evaneostest.model.entity.UIStateResponse
import com.evaneos.evaneostest.ui.adapters.DestinationDataAdapter
import com.evaneos.evaneostest.ui.isInstanceOf
import com.evaneos.evaneostest.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: DestinationDataAdapter
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private lateinit var erreur: TextView
    private lateinit var refresh: Button
    private lateinit var mprogressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        mMainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mMainActivityViewModel.viewState.observe(this) { uiState ->
            when (uiState) {
                is UIStateResponse.Loading -> {
                    mprogressBar.visibility = View.VISIBLE
                    mRecyclerView.visibility = View.GONE
                    erreur.visibility = View.GONE
                    refresh.visibility = View.GONE
                }
                is UIStateResponse.Error -> {
                    erreur.visibility = View.VISIBLE
                    mprogressBar.visibility = View.GONE
                    mRecyclerView.visibility = View.GONE
                    erreur.text = uiState.errorMessage
                    refresh.visibility = View.VISIBLE
                    clickOnRefreshButton()
                }
                is UIStateResponse.Success<*> -> {
                    mRecyclerView.visibility = View.VISIBLE
                    mprogressBar.visibility = View.GONE
                    erreur.visibility = View.GONE
                    refresh.visibility = View.GONE
                    if (isInstanceOf<UIStateResponse.Success<List<Destination>>>(uiState))
                        setRecyclerView(uiState as UIStateResponse.Success<List<Destination>>)
                }
            }
        }

    }

    private fun setRecyclerView(response: UIStateResponse.Success<List<Destination>>) {
        mAdapter = DestinationDataAdapter(this, response.content)
        recyclerViewInitView()
    }

    private fun initView() {
        mRecyclerView = findViewById(R.id.main_DestinationRV)
        erreur = findViewById(R.id.erreurTv)
        refresh = findViewById(R.id.refresh_button)
        mprogressBar = findViewById(R.id.progress_bar)
    }

    //Met à jour la liste des Destinations en cas d'erreur
    private fun clickOnRefreshButton() {
        refresh.setOnClickListener {
            onRefreshButtonClicked()
        }
    }

    private fun recyclerViewInitView() {
        val linearLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.adapter = mAdapter
    }

    //Reactualise l'activity pour reloader les données en cas d'erreur
    private fun onRefreshButtonClicked() {
        with(mMainActivityViewModel) {
            getDestinations()
        }
    }
}