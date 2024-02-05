package com.example.earthquakedataapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.earthquakedataapp.R
import com.example.earthquakedataapp.model.EarthquakeData
import com.example.earthquakedataapp.model.Result

@Composable
fun HomeScreen(
    uiState: UiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    when (uiState) {
        is UiState.Loading -> LoadingScreen(modifier = modifier)
        is UiState.Success -> LoadData(
            data = uiState.data,
            contentPadding = contentPadding,
            modifier = modifier.fillMaxWidth()
        )

        is UiState.Error -> ErrorScreen(retryAction = retryAction, modifier = modifier)

    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier
){
    Image(painter = painterResource(id = R.drawable.loading_img),
            modifier = modifier.size(200.dp),
        contentDescription = null)
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = "" )

        Text(text = "Loading failed", modifier = Modifier.padding(16.dp))
        
        Button(onClick = retryAction) {
            Text(text = "retry")
            
        }

    }

}

@Composable
fun LoadData(
    data : EarthquakeData,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
        ) {

        val list = data.result
        items(items = list, key = {card -> card}) {
            EarthquakeDataCard(
                it,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}

@Composable
fun EarthquakeDataCard(data : Result, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        data.title?.let { Text(text = it) }
    }
}