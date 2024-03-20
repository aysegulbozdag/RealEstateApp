package com.example.realestateapp.di


import com.example.realestateapp.data.repository.RealEstateRepository
import com.example.realestateapp.data.repository.RealEstateRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindDataRepository(realEstateRepository: RealEstateRepositoryImpl): RealEstateRepository
}