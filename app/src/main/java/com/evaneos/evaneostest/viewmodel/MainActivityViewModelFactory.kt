package com.evaneos.evaneostest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DestWebViewModelFactory(id: Long) : ViewModelProvider.Factory {
    private val dest_Id: Long

    init {
        dest_Id = id
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Show_Dest_WebViewModel(dest_Id) as T
    }
}