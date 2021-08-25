package com.evaneos.evaneostest.ui.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.evaneos.data.model.Destination
import com.evaneos.evaneostest.ui.activity.DestinationDetail_WebView
import com.evaneos.evaneostest.utils.Constants

class ItemDestinationViewModel(context: Context, destination: Destination) :
    ViewModel() {
    val destinationDetail = destination
    val contexte = context

    fun onItemClick() {
        val intent = Intent(
            contexte,
            DestinationDetail_WebView::class.java
        )
        intent.putExtra(Constants.INTENT_EXTRA_ID, destinationDetail.id)
        intent.putExtra(Constants.INTENT_EXTRA_NAME, destinationDetail.name)
        contexte.startActivity(intent)
    }
}
