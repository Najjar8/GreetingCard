package com.example.greetingcard.activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.greetingcard.R
import com.example.greetingcard.network.GalleryPhoto
import com.example.greetingcard.designsystem.ImageViewModel
import com.example.greetingcard.designsystem.ImgUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryPage(navController: NavController, modifier: Modifier){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MyTopAppBar(
                onBackClick = { navController.popBackStack() },
                barLabel = "Gallery",
                scrollBehavior = scrollBehavior
            )
        }
    ) {innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            val imageViewModel: ImageViewModel = viewModel()
            val imgUiState: ImgUiState = imageViewModel.imgUiState
            when (imgUiState) {
                is ImgUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
                is ImgUiState.Success -> ResultScreen(imgUiState.photos, modifier = modifier.fillMaxWidth())
                is ImgUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
            }

        }
    }

}

@Composable
fun GalleryGrid(photos: List<GalleryPhoto>, modifier: Modifier = Modifier){
    val ImgUrls = photos.map { photo -> photo.imgSrc }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1), // 3 columns
    ) {
        items(ImgUrls) { url ->
            AsyncImage(
                model = url,
                contentDescription = "Photo",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // square images
            )
        }
    }
}

@Composable
fun ResultScreen(photos: List<GalleryPhoto>, modifier: Modifier = Modifier) {
    GalleryGrid(photos = photos, modifier = modifier)
}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(150.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = "loading"
    )
}
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_icon), contentDescription = ""
        )
        Text(text = "Error", modifier = Modifier.padding(16.dp))
    }
}
