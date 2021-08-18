package com.evaneos.evaneostest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.data.model.DestinationDetails
import com.evaneos.evaneostest.repositories.DestinationDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Show_Dest_WebViewModel(): ViewModel() {
    private var destinationsDetails = MutableLiveData<DestinationDetails?>()
    private val destinationDetailsRepository = DestinationDetailsRepository(
        FakeDestinationFetchingService()
    )

    private val _progressbar = MutableLiveData<Boolean>(false)
    private val _errorMessage = MutableLiveData<String?>()

    val progressBar: LiveData<Boolean>
        get() = _progressbar
    val errorMessage: LiveData<String?>
        get() = _errorMessage


    fun getDestinationsDetails(id: Long): LiveData<DestinationDetails?> {
        viewModelScope.launch {
            launchDataLoad {
                try {
                    val destinationsDataDetails = withContext(Dispatchers.IO) {

                        destinationDetailsRepository.getDestinationsDetailsList(id)
                    }

                    destinationsDetails.value = destinationsDataDetails

                } catch (error: Throwable) {
                    _errorMessage.value = error.message
                }
            }

        }
        return destinationsDetails
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _progressbar.value = true
                block()
            } catch (error: Throwable) {
                _errorMessage.value = error.message
            } finally {
                _progressbar.value = false
            }
        }
    }
}