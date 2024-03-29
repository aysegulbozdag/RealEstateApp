package com.example.realestateapp.di

import com.example.realestateapp.domain.GetHouseListUseCaseImpl
import com.example.realestateapp.domain.HouseListUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun bindGetHouseListUseCase(getHouseListUseCaseImpl: GetHouseListUseCaseImpl): HouseListUseCase

}