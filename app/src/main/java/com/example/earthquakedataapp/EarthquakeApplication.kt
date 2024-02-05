package com.example.earthquakedataapp

import android.app.Application
import com.example.earthquakedataapp.data.AppContainer
import com.example.earthquakedataapp.data.DefaultAppContainer

class EarthquakeApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}