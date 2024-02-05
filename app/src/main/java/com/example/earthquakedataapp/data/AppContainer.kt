package com.example.earthquakedataapp.data

import com.example.earthquakedataapp.network.EarthquakeApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val earthquakeRepository: EarthQuakeRepository
}

class DefaultAppContainer() : AppContainer {

    private val baseUrl = "https://api.orhanaydogdu.com.tr/"


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()


    private val retrofitService: EarthquakeApiService by lazy {
        retrofit.create(EarthquakeApiService::class.java)
    }

    override val earthquakeRepository: EarthQuakeRepository by lazy {
        EarthQuakeRepositoryImpl(retrofitService)
    }

}