package com.example.earthquakedataapp.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.earthquakedataapp.ui.screen.EarthquakeViewModel
import com.example.earthquakedataapp.ui.screen.HomeScreen
import androidx.lifecycle.viewmodel.compose.viewModel


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarthquakeApp() {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            EarthquakeTopBar(scrollBehavior = scrollBehavior)
        }
    ) {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val model: EarthquakeViewModel = viewModel(factory = EarthquakeViewModel.Factory)
            HomeScreen(
                uiState = model.uistate,
                retryAction = model::getEarthquakeData,
                contentPadding = it
            )

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarthquakeTopBar(
    scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Earthquake app",
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}