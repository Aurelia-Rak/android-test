package com.evaneos.evaneostest.repositories

import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.data.model.DestinationDetails

class DestinationDetailsRepository(private val fetchingService: FakeDestinationFetchingService) {
    suspend fun getDestinationsDetailsList(id: Long): DestinationDetails {
        return fetchingService.getDestinationDetails(id)
    }
}