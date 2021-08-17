package com.evaneos.data

import com.evaneos.data.model.Destination
import com.evaneos.data.model.DestinationDetails

interface DestinationFetchingService {
    suspend fun getDestinations() : List<Destination>
    suspend fun getDestinationDetails(id: Long) : DestinationDetails
}