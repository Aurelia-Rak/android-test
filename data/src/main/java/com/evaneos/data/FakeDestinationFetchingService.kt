package com.evaneos.data

import com.evaneos.data.factory.DataFactory
import com.evaneos.data.model.Destination
import com.evaneos.data.model.DestinationDetails
import kotlinx.coroutines.delay
import kotlin.random.Random

class FakeDestinationFetchingService : DestinationFetchingService {
    override suspend fun getDestinations() : List<Destination> = withFakeDelay {
        val number = Random.nextInt(1, 5)
       if (number == 1) {
            throw Exception("Oops, something went wrong")
        }

        if (number == 2) {
            emptyList()
        } else {
            DataFactory.createDestinationList()
        }
    }

    override suspend fun getDestinationDetails(id: Long) : DestinationDetails = withFakeDelay {
        if (id == 170L) {
            throw Exception("Oops, something went wrong")
        }

        DataFactory.getDestinationDetails(id)
    }

    private suspend fun <T> withFakeDelay(action: suspend () -> T): T {
        // Fake network delay
        delay(Random.nextLong(200, 5000))
        return action()
    }
}