package com.example.realestateapp.domain


import com.example.realestateapp.data.NetworkResponseState
import com.example.realestateapp.data.model.HouseList
import kotlinx.coroutines.flow.Flow

interface HouseListUseCase {
    operator fun invoke() : Flow<NetworkResponseState<HouseList>>
}