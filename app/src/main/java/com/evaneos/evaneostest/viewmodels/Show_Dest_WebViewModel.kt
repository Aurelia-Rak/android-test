package com.evaneos.evaneostest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.data.model.DestinationDetails
import com.evaneos.evaneostest.repositories.DestinationDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Show_Dest_WebViewModel(): ViewModel() {
    private var destinationsDetails = MutableLiveData<DestinationDetails>()
    private val destinationDetailsRepository = DestinationDetailsRepository(
        FakeDestinationFetchingService()
    )

    fun getDestinationsDetails(id: Long): LiveData<DestinationDetails> {
        viewModelScope.launch {
            val destinationsDataDetails = withContext(Dispatchers.IO) {

                destinationDetailsRepository.getDestinationsDetailsList(id)
            }
            destinationsDetails.value = destinationsDataDetails
        }
        return destinationsDetails
    }
}