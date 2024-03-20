package com.example.realestateapp.data

sealed class NetworkResponseState<out T : Any> {
    object Loading : NetworkResponseState<Nothing>()
    data class Error(val exception: String) : NetworkResponseState<Nothing>()
    data class Success<out T : Any>(val result: T?) : NetworkResponseState<T>()
    object Empty : NetworkResponseState<Nothing>()
}