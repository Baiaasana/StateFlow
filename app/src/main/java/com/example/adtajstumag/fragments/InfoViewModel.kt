package com.example.adtajstumag.fragments

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adtajstumag.LatestInfoUiState
import com.example.adtajstumag.data.ItemModel
import com.example.adtajstumag.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class InfoViewModel : ViewModel() {

    private val _infoState = MutableStateFlow(LatestInfoUiState.Success(emptyList()))
    val infoState: StateFlow<LatestInfoUiState> = _infoState

    private val _loaderState = MutableStateFlow(true)
    val loaderState: StateFlow<Boolean> = _loaderState

    fun getInfo() {

        viewModelScope.launch {

            _loaderState.value = true

            val response = RetrofitClient.Services().getInfo()
            if (response.isSuccessful){
                val body : ItemModel = response.body()!!
                _infoState.value = LatestInfoUiState.Success(body.content)
                d("body", "${body.content}")
            }
//
//            else{
//                val errorBody = response.errorBody()
//                _infoState.value = LatestInfoUiState.Error("dsd")
//            }
            _loaderState.value = false
        }
    }
}