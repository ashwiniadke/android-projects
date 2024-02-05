package com.example.earthquakedataapp.ui.screen

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.earthquakedataapp.EarthquakeApplication
import com.example.earthquakedataapp.data.EarthQuakeRepository
import com.example.earthquakedataapp.model.EarthquakeData
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UiState {
    data class Success(val data: EarthquakeData) : UiState
    object Error : UiState
    object Loading : UiState
}
class EarthquakeViewModel(
    private val repository: EarthQuakeRepository
): ViewModel() {

    var uistate: UiState by mutableStateOf(UiState.Loading)
        private set

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getEarthquakeData() {
        viewModelScope.launch {
            uistate = UiState.Loading
            uistate = try {
                UiState.Success(repository.getEarthquakeData())
            } catch (e: IOException) {
                UiState.Error
            } catch (e: HttpException) {
                UiState.Error
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as EarthquakeApplication)
                val repo = application.container.earthquakeRepository
                EarthquakeViewModel(repo)
            }
        }
    }
}