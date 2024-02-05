package com.example.earthquakedataapp.network

import com.example.earthquakedataapp.model.EarthquakeData
import retrofit2.http.GET

interface EarthquakeApiService {

    @GET("deprem/kandilli/live")
    suspend fun getEarthquakes(): EarthquakeData
}