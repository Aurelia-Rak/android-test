package com.evaneos.evaneostest.repositories

import android.util.Log
import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.data.model.Destination

class DestinationRepository(private val fetchingService: FakeDestinationFetchingService) {

    suspend fun getDestinationsList(): List<Destination> {
        Log.d("reactualisation", "résultat")
        return fetchingService.getDestinations()
    }

    private fun shouldUpdateDestinationCache(): Boolean {
        return true
    }

    suspend fun tryUpdateRecentDestinationCache() {
        if (shouldUpdateDestinationCache()) getDestinationsList()
    }

}