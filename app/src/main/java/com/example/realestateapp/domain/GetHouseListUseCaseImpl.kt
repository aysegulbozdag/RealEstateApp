package com.example.realestateapp.domain

import com.example.realestateapp.data.NetworkResponseState
import com.example.realestateapp.data.model.HouseList
import com.example.realestateapp.data.repository.RealEstateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHouseListUseCaseImpl @Inject constructor(private val realEstateRepository: RealEstateRepository) :  HouseListUseCase{
    override fun invoke(): Flow<NetworkResponseState<HouseList>> = flow {
        emit(NetworkResponseState.Loading)
        when (val response = realEstateRepository.getHouseList()) {
            is NetworkResponseState.Error -> emit(response)
            NetworkResponseState.Loading -> Unit
            is NetworkResponseState.Success -> emit(
                NetworkResponseState.Success(
                    response.result
                )
            )
        }
    }
}