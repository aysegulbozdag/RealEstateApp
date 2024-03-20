package com.example.realestateapp.data.model

data class Data(
    val bathCount: Int,
    val buildingAge: String,
    val category: String,
    val city: String,
    val createdDate: String,
    val curreny: String,
    val description: String,
    val district: String,
    val gross: Int,
    val images: List<String>,
    val label: String,
    val neighborhood: String,
    val net: Int,
    val price: String,
    val room: String,
    val roomCount: Int
)