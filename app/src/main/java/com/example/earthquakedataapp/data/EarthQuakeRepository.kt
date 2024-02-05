package com.example.earthquakedataapp.data

import com.example.earthquakedataapp.model.EarthquakeData
import com.example.earthquakedataapp.network.EarthquakeApiService

interface EarthQuakeRepository {

    suspend fun getEarthquakeData(): EarthquakeData
}

class EarthQuakeRepositoryImpl(
    private val apiService: EarthquakeApiService
): EarthQuakeRepository {
    override suspend fun getEarthquakeData(): EarthquakeData = apiService.getEarthquakes()
}