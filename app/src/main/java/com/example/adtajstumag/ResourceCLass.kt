package com.example.adtajstumag

import com.example.adtajstumag.data.ItemModel

sealed class LatestInfoUiState {
    data class Success(val info: List<ItemModel.Item>): LatestInfoUiState()
    data class Error(val error: String): LatestInfoUiState()
}
