package com.example.realestateapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestateapp.data.NetworkResponseState
import com.example.realestateapp.data.model.Data
import com.example.realestateapp.data.model.HouseList
import com.example.realestateapp.domain.HouseListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: HouseListUseCase) : ViewModel() {

    private var _listState: MutableStateFlow<NetworkResponseState<HouseList>> =
        MutableStateFlow(NetworkResponseState.Loading)
    val listState: StateFlow<NetworkResponseState<HouseList>> = _listState.asStateFlow()

    init {
        getHouseList()
    }

    private fun getHouseList() {
        viewModelScope.launch {
            useCase.invoke().collect {
                _listState.value =
                    it

            }
        }
    }

    fun searchList(query: String, dataList: List<Data>): List<Data> =
        dataList.filter { item ->
            item.category.contains(query, ignoreCase = true)
        }

}