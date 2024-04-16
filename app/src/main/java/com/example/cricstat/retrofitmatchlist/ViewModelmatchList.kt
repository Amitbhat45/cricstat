package com.example.cricstat.retrofitmatchlist

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricstat.retrofitmatchlist.dataclass.matchList
import com.example.cricstat.retrofitmatchlist.retrofitInstance.ProvideApi
import com.example.cricstat.retrofitmatchlist.retrofitInstance.provideRetrofit

import kotlinx.coroutines.launch



class ViewModelmatchList  (private val repository: Repository1):ViewModel(){
    private val _liveMatches = MutableLiveData<matchList>()
    val liveMatches: LiveData<matchList>get() = _liveMatches

    private val _upcomingMatches = MutableLiveData<com.example.cricstat.retrofitmatchlist.dataclass.matchList>()
    val upcomingMatches: LiveData<com.example.cricstat.retrofitmatchlist.dataclass.matchList> get() = _upcomingMatches

    private val _recentMatches = MutableLiveData<com.example.cricstat.retrofitmatchlist.dataclass.matchList>()
    val recentMatches: LiveData<com.example.cricstat.retrofitmatchlist.dataclass.matchList> get() = _recentMatches

    /*private val _imageBytes = MutableLiveData<ByteArray?>()
    val imageBytes: MutableLiveData<ByteArray?> get() = _imageBytes*/

    private val _imageBitmap = MutableLiveData<Bitmap>()
    val imageBitmap: LiveData<Bitmap> get() = _imageBitmap

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    init {
        fetchLiveMatches()
        fetchUpcomingMatches()
        fetchRecentMatches()

    }

    /*init {
        viewModelScope.launch {
            val retrofit = provideRetrofit() // Background network call for Retrofit
            val matchListService = ProvideApi(retrofit)
            val repository = Repository1(matchListService)

            // Use the created repository instance here (optional)
        }
    }*/

    private fun fetchLiveMatches() {
        viewModelScope.launch {
            val response = repository.getLiveMatches()
            if (response != null) {
                if (response.isSuccessful) {
                    _liveMatches.value = response.body()
                }
            }
        }
    }

    private fun fetchUpcomingMatches() {
        viewModelScope.launch {
            val response = repository.getUpcomingMatches()
            if (response != null) {
                if (response.isSuccessful) {
                    _upcomingMatches.value = response.body()
                }
            }
        }
    }

    private fun fetchRecentMatches() {
        viewModelScope.launch {
            val response = repository.getRecentMatches()
            if (response != null) {
                if (response.isSuccessful) {
                    _recentMatches.value = response.body()
                }
            }
        }
    }

/*    fun fetchImage(imageId: String) {
        viewModelScope.launch {
            val response = repository.getImage(imageId)
            if (response != null && response.isSuccessful) {
                // Convert the ResponseBody to a byte array
                val imageBytes = response.body()?.bytes()
                _imageBytes.value = imageBytes
            }
        }
    }*/

  /*  fun fetchImage(imageId: String) {
        viewModelScope.launch {
            val response = repository.getImage(imageId)
            if (response != null) {
                if (response.isSuccessful) {
                    // Convert the ResponseBody to a Bitmap
                    val bitmap = BitmapFactory.decodeStream(response.body()?.byteStream())
                    // Update LiveData with the Bitmap
                    _imageBitmap.value = bitmap
                } else {
                    // Handle the error
                    val errorBody = response.errorBody()?.string()
                    Log.e("YourViewModel", "Error fetching image: $errorBody")
                    // Update LiveData to reflect the error state
                    _error.value = "Failed to fetch image"
                }
            }
        }
    }*/

}




