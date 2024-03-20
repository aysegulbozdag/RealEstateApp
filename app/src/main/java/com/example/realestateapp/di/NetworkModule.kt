package com.example.realestateapp.di

import com.example.realestateapp.data.api.RealEstateApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    @ViewModelScoped
    fun provideUserInvoiceApi(): RealEstateApi {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/ofarukcelik/ofarukcelik/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RealEstateApi::class.java)
    }
}