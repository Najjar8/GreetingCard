package com.example.greetingcard.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.greetingcard.ui.theme.GreetingCardTheme


val imageUrls = listOf(
    "https://picsum.photos/id/231/200/300",
    "https://picsum.photos/id/232/200/300",
    "https://picsum.photos/id/233/200/300",
    "https://picsum.photos/id/234/200/300",
    "https://picsum.photos/id/235/200/300",
    "https://picsum.photos/id/236/200/300",
    "https://picsum.photos/id/237/200/300",
    "https://picsum.photos/id/238/200/300",
    "https://picsum.photos/id/239/200/300",
    "https://picsum.photos/id/240/200/300",
    "https://picsum.photos/id/241/200/300",
    "https://picsum.photos/id/244/200/300",
    "https://picsum.photos/id/247/200/300",
    "https://picsum.photos/id/248/200/300",
    "https://picsum.photos/id/249/200/300",

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagesPage(navController: NavController, modifier: Modifier){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets(0)
        ,
    topBar = {
            MyTopAppBar(
                onBackClick = { navController.popBackStack() },
                barLabel = "Grid",
                scrollBehavior = scrollBehavior
            )
        }
    ) {innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ImageFromUrl()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageFromUrl() {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 columns
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            Spacer(modifier = Modifier.height(90.dp))
        }
        items(imageUrls) { url ->
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

@Preview(showBackground = true)
@Composable
fun ImagesPagePreview(){
    GreetingCardTheme {
        ImagesPage(navController = rememberNavController(), modifier = Modifier)
    }
}