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

class Show_Dest_WebViewModel internal constructor(
    destId: Long
) : ViewModel() {
    private var _destinationsDetails = MutableLiveData<DestinationDetails?>()
    private val destinationDetailsRepository = DestinationDetailsRepository(
        FakeDestinationFetchingService()
    )
    private var id = destId

    private val _progressbar = MutableLiveData<Boolean>(false)
    private val _errorMessage = MutableLiveData<String?>()


    val progressBar: LiveData<Boolean>
        get() = _progressbar
    val errorMessage: LiveData<String?>
        get() = _errorMessage
    val destionationDetails: LiveData<DestinationDetails?>
        get() = _destinationsDetails

    init {
        getDestinationsDetails()
    }

    fun getDestinationsDetails() {
        launchDataLoad {
            try {
                val destinationsDataDetails = withContext(Dispatchers.IO) {

                    destinationDetailsRepository.getDestinationsDetailsList(id)
                }

                _destinationsDetails.value = destinationsDataDetails

            } catch (error: Throwable) {
                _errorMessage.value = error.message
            }

        }
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