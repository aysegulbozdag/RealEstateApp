package com.example.realestateapp.data.repository


import com.example.realestateapp.data.NetworkResponseState
import com.example.realestateapp.data.api.RealEstateApi
import com.example.realestateapp.data.model.Data
import com.example.realestateapp.data.model.HouseList
import com.example.realestateapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RealEstateRepositoryImpl @Inject constructor(
    private val realEstateApi: RealEstateApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    RealEstateRepository {

    override suspend fun getHouseList(): NetworkResponseState<HouseList> =
        withContext(ioDispatcher) {
            try {
                NetworkResponseState.Success(realEstateApi.getHouseList())
            } catch (e: Exception) {
                NetworkResponseState.Error(e.message.toString())
            }
        }
}