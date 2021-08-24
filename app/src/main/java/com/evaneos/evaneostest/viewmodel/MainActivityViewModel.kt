package com.evaneos.evaneostest.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.data.model.Destination
import com.evaneos.evaneostest.model.entity.UIStateResponse
import com.evaneos.evaneostest.repositories.DestinationRepository
import com.evaneos.evaneostest.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {
    private val destinationRepository = DestinationRepository(FakeDestinationFetchingService())
    private val defaultError = "Something went wrong."
    private val _viewState = MutableLiveData<UIStateResponse>()
    val navigateToDetail = SingleLiveEvent<Destination>()
    val viewState: LiveData<UIStateResponse> = _viewState

    init {
        getDestinations()
    }

    fun getDestinations() {
        launchDataLoad {
            val destinationsData = withContext(Dispatchers.IO) {
                destinationRepository.getDestinationsList().sortedBy { it.name }
            }
            if (!destinationsData.isEmpty()) {
                _viewState.postValue(UIStateResponse.Success(destinationsData))
            } else {
                _viewState.postValue(UIStateResponse.Error(defaultError))
            }
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _viewState.postValue(UIStateResponse.Loading)
                block()
            } catch (error: Throwable) {
                _viewState.postValue(error.message?.let { UIStateResponse.Error(it) })
            }
        }
    }

    fun itemClick(destinationListeDetail: Destination) {
        navigateToDetail.value = destinationListeDetail
    }

    fun refreshPage() {
        Log.d("ici message", "refresh")
        getDestinations()
    }
}