package com.example.adtajstumag.fragments

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adtajstumag.LatestInfoUiState
import com.example.adtajstumag.data.ItemModel
import com.example.adtajstumag.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InfoViewModel : ViewModel() {

    private val _infoState = MutableStateFlow<LatestInfoUiState<List<ItemModel.Item>>>(LatestInfoUiState.Success(
        emptyList()))
    val infoState = _infoState.asStateFlow()

    fun getInfo() {

        viewModelScope.launch {

            _infoState.value = LatestInfoUiState.Loader(true)

            val response = RetrofitClient.retrofitBuilder.getInfo()
            if (response.isSuccessful){
                val body : ItemModel = response.body()!!
                _infoState.value = LatestInfoUiState.Success(body.content)
                d("body", "${body.content}")
            }else{
                val errorBody = response.errorBody()
                _infoState.value = LatestInfoUiState.Error(errorBody.toString()?: "")
            }

            _infoState.value = LatestInfoUiState.Loader(false)
        }
    }
}