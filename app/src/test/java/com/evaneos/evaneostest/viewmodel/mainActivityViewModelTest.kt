package com.evaneos.evaneostest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.evaneos.data.FakeDestinationFetchingService
import com.evaneos.evaneostest.model.MockDestinationDataProvider.Companion.getMockedDestination
import com.evaneos.evaneostest.repositories.DestinationRepository
import com.evaneos.evaneostest.viewmodels.MainActivityViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock

class MainActivityModelViewTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    var destinationRepository: DestinationRepository? = null
    private var viewModel: MainActivityViewModel? = null

    @Before
    fun setUp() {
        destinationRepository = DestinationRepository(FakeDestinationFetchingService())
    }

    @Test
    fun TestDestinationInitialValueShouldBeNull() = runBlocking {
        viewModel?.destinationRepository.let {
            assertEquals(it?.getDestinationsList(), null)
        }
    }

    @Test
    fun GetDestinationsWithReturnANULL() = runBlocking {
        viewModel?.getDestinations()
        Assert.assertNull(viewModel?.destinationRepository)
    }

    @Test
    fun compareDestinationListwithMockData() = runBlocking {
        val mockedData = getMockedDestination()

        viewModel = MainActivityViewModel()

        assertEquals(mockedData, viewModel!!.destinationRepository.getDestinationsList())
    }
}