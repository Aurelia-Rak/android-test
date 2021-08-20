package com.evaneos.evaneostest.model.entity

sealed class UIStateResponse {
    object Loading : UIStateResponse()
    data class Error(val errorMessage: String) : UIStateResponse()
    data class Success<T>(val content: T) : UIStateResponse() {
    }
}