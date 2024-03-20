package com.example.realestateapp.data.repository

import com.example.realestateapp.data.NetworkResponseState
import com.example.realestateapp.data.model.Data
import com.example.realestateapp.data.model.HouseList

interface RealEstateRepository {
    suspend fun getHouseList() : NetworkResponseState<HouseList>

}