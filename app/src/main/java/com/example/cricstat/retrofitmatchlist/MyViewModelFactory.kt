package com.example.cricstat.retrofitmatchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MyViewModelFactory(private val repository: Repository1) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelmatchList::class.java)) {
            return ViewModelmatchList(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}