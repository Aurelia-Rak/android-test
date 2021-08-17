package com.evaneos.evaneostest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.data.model.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel:ViewModel() {
    private var destinationsList = MutableLiveData<List<Destination>>()
    private val fakeDestinationFetchingService = FakeDestinationFetchingService()

    fun getDestinations(): LiveData<List<Destination>> {
        viewModelScope.launch {
            val destinationsData = withContext(Dispatchers.IO) {

                fakeDestinationFetchingService.getDestinations()
            }
            destinationsList.value = destinationsData
        }
        return destinationsList
    }
}