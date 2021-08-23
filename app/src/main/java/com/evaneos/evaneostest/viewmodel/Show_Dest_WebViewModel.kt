package com.evaneos.evaneostest.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.evaneostest.model.entity.UIStateResponse
import com.evaneos.evaneostest.repositories.DestinationDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Show_Dest_WebViewModel internal constructor(
    destId: Long
) : ViewModel() {
    private val destinationDetailsRepository =
        DestinationDetailsRepository(FakeDestinationFetchingService())
    private var id = destId
    private val _viewState_wv = MutableLiveData<UIStateResponse>()
    val viewState_wv: LiveData<UIStateResponse> = _viewState_wv

    init {
        getDestinationsDetails()
    }

    fun getDestinationsDetails() {
        launchDataLoad {
            try {
                val destinationsDataDetails = withContext(Dispatchers.IO) {
                    destinationDetailsRepository.getDestinationsDetailsList(id)
                }
                _viewState_wv.postValue(UIStateResponse.Success(destinationsDataDetails))
            } catch (error: Throwable) {
                _viewState_wv.postValue(error.message?.let { UIStateResponse.Error(it) })
            }
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            _viewState_wv.postValue(UIStateResponse.Loading)
                block()
        }
    }
}