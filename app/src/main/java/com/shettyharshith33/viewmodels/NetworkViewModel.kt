package com.shettyharshith33.viewmodels


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shettyharshith33.utils.checkInternetConnection

class NetworkViewModel(application: Application) : AndroidViewModel(application) {
    private val connectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean> = _isConnected

    init {
        _isConnected.value = checkInternetConnection(connectivityManager)

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _isConnected.postValue(true)
            }

            override fun onLost(network: Network) {
                _isConnected.postValue(false)
            }
        }

        val networkRequest = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}
