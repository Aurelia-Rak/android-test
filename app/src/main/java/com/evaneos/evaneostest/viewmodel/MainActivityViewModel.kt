package com.evaneos.evaneostest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.data.model.Destination
import com.evaneos.evaneostest.repositories.DestinationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {
    private var _destinationsList = MutableLiveData<List<Destination>>()
    private val destinationRepository = DestinationRepository(FakeDestinationFetchingService())
    private val _progressbar = MutableLiveData<Boolean>(false)
    private val _errorMessage = MutableLiveData<String?>()
    private val defaultError = "Something went wrong."

    val progressBar: LiveData<Boolean>
        get() = _progressbar
    val errorMessage: LiveData<String?>
        get() = _errorMessage
    val destinationsList: LiveData<List<Destination>>
        get() = _destinationsList

    init {
        getDestinations()
    }

    fun getDestinations() {
        launchDataLoad {
            val destinationsData = withContext(Dispatchers.IO) {
                destinationRepository.getDestinationsList()
            }
            if (!destinationsData.isEmpty())
                _destinationsList.value = destinationsData.sortedBy { it.name }
            else
                _errorMessage.value = defaultError
        }
    }

    fun updateDestinationList() {
        _destinationsList.value = ArrayList<Destination>()
        launchDataLoad { getDestinations() }
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

    fun itemClick() {

    }
}