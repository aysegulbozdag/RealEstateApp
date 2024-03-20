package com.example.realestateapp.data.api

import com.example.realestateapp.data.NetworkResponseState
import com.example.realestateapp.data.model.Data
import com.example.realestateapp.data.model.HouseList
import retrofit2.http.GET

interface RealEstateApi {

    @GET("android-test-case.json")
    suspend fun getHouseList() : HouseList
}