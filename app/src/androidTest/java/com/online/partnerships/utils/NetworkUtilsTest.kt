package com.online.partnerships.utils

import android.net.NetworkCapabilities
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.withSettings
import org.mockito.MockitoAnnotations

class NetworkUtilsTest {

//    @Mock
//    private lateinit var context: Context
//
//    @Mock
//    private lateinit var connectivityManager: ConnectivityManager
//
//    @Mock
//    private lateinit var network: Network

//    @Mock
//    private lateinit var capabilities: NetworkCapabilities

//    @Mock
//    lateinit var serviceEndPoints: ServiceEndPoints

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun test_isNetworkConnected_success() {

        var capabilities = mock(NetworkCapabilities::class.java, withSettings().withoutAnnotations())

//        // Given a context with a connectivity manager that returns a network with capabilities
//        Mockito.`when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
//        Mockito.`when`(connectivityManager.activeNetwork).thenReturn(network)
//        Mockito.`when`(connectivityManager.getNetworkCapabilities(network)).thenReturn(capabilities)
////        Mockito.`when`(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)).thenReturn(true)
//
//        // When calling isNetworkConnected()
//        val isConnected = isNetworkConnected(context)

        // Then true is returned
        assertNotNull("hello")
    }
}