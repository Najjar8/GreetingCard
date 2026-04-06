package com.example.greetingcard.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.greetingcard.ui.theme.GreetingCardTheme
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import com.example.greetingcard.Screens



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController, modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            MyTopAppBar(onBackClick = { navController.popBackStack() }, barLabel = "Home", scrollBehavior = scrollBehavior)
        }
    ) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            ButtonGrid(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    onBackClick: () -> Unit,
    barLabel: String = "TopAppBar",
    scrollBehavior: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = barLabel)
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(4, 83, 90),
            scrolledContainerColor = Color(4, 83, 90),
            navigationIconContentColor = Color(4, 83, 90),
            titleContentColor = Color.White,
            actionIconContentColor = Color.Transparent
        )
    )
}

@Composable
fun ButtonGrid(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize().fillMaxWidth().padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        HomeButton(onClick = {navController.navigate(Screens.Images.name)}, text = "Grid")
        HomeButton(onClick = {navController.navigate(Screens.Gallery.name)}, text = "Gallery")
        HomeButton(onClick = {navController.navigate(Screens.Tasks.name)}, text = "Tasks")
        HomeButton(onClick = {navController.navigate(Screens.Test.name)}, text = "Profile")

    }

}

@Composable
fun HomeButton(onClick: () -> Unit, text: String){
    Button(
        onClick = {onClick()},
        modifier = Modifier.height(50.dp).fillMaxWidth(),
        colors = buttonColors(containerColor = Color(4, 83, 200))
    ){
        Text(text)
    }
}


@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    GreetingCardTheme {
        HomePage(navController = rememberNavController())
    }
}