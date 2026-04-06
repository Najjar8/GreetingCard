package com.example.greetingcard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greetingcard.activities.GalleryPage
import com.example.greetingcard.activities.HomePage
import com.example.greetingcard.activities.ImagesPage
import com.example.greetingcard.activities.LoginPage
import com.example.greetingcard.activities.ProfileScreen
import com.example.greetingcard.activities.SplashScreen
import com.example.greetingcard.activities.TasksPage

enum class Screens{
    Home,
    Login,
    Splash,
    Images,
    Gallery,
    Tasks,
    Test
}
@Composable
fun MyAppNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Splash.name, builder = {

        composable(Screens.Splash.name){
            SplashScreen(onSplashFinished = {
                navController.navigate(Screens.Login.name)
            })
        }

        composable(Screens.Login.name){
            LoginPage(navController, modifier = modifier)
        }

        composable(Screens.Home.name){
            HomePage(navController, modifier = modifier)
        }

        composable(Screens.Images.name){
            ImagesPage(navController, modifier = modifier)
        }

        composable(Screens.Gallery.name){
            GalleryPage(navController, modifier = modifier)
        }
        composable(Screens.Tasks.name){
            TasksPage(navController, modifier = modifier)
        }
        composable(Screens.Test.name){
            ProfileScreen(navController)
        }
    })

}