package com.evaneos.evaneostest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.data.model.Destination
import com.evaneos.evaneostest.repositories.DestinationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel:ViewModel() {
    private var destinationsList = MutableLiveData<List<Destination>>()
    private val destinationRepository = DestinationRepository(FakeDestinationFetchingService())

    fun getDestinations(): LiveData<List<Destination>> {
        viewModelScope.launch {
            try {
                val destinationsData = withContext(Dispatchers.IO) {

                    destinationRepository.getDestinationsList()
                }
                destinationsList.value = destinationsData
            } catch (error: Throwable) {
            }
        }
        return destinationsList
    }

}