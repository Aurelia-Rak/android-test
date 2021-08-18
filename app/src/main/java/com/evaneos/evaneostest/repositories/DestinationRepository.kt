package com.evaneos.evaneostest.repositories

import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.data.model.Destination

class DestinationRepository(private val fetchingService: FakeDestinationFetchingService) {

    suspend fun getDestinationsList(): List<Destination> {
        return fetchingService.getDestinations()
    }
}