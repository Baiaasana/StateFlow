package com.example.adtajstumag

sealed class LatestInfoUiState<out T: Any> {

    data class Success<out T: Any>(val info: T): LatestInfoUiState<T>()
    data class Error(val error: String): LatestInfoUiState<Nothing>()
    data class Loader(val isLoading: Boolean): LatestInfoUiState<Nothing>()
}