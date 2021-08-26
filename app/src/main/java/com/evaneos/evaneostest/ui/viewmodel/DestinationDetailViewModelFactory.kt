package com.evaneos.evaneostest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DestWebViewModelFactory(id: Long) : ViewModelProvider.Factory {
    private val destinationId: Long

    init {
        destinationId = id
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DestinationDetail_WebViewModel(destinationId) as T
    }
}