package com.evaneos.evaneostest.model.entity

import com.evaneos.data.model.Destination

sealed class UIStateResponse {
    object Loading : UIStateResponse()
    data class Error(val errorMessage: String) : UIStateResponse()
    data class Success(val destinationsList: List<Destination>) : UIStateResponse()
}