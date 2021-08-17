package com.evaneos.evaneostest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.data.model.Destination
import com.evaneos.data.model.DestinationDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Show_Dest_WebViewModel(): ViewModel() {
    private var destinationsDetails = MutableLiveData<DestinationDetails>()
    private val fakeDestinationFetchingService = FakeDestinationFetchingService()

    fun getDestinationsDetails(id: Long): LiveData<DestinationDetails> {
        viewModelScope.launch {
            val destinationsDataDetails = withContext(Dispatchers.IO) {

                fakeDestinationFetchingService.getDestinationDetails(id)
            }
            destinationsDetails.value = destinationsDataDetails
        }
        return destinationsDetails
    }
}