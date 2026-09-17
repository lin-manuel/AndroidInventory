package com.were.myfirstapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.were.myfirstapp.ui.theme.screens.dash.DashScreen
import com.were.myfirstapp.ui.theme.screens.home.Homescreen
import com.were.myfirstapp.ui.theme.screens.intent.Intentscreen
import com.were.myfirstapp.ui.theme.screens.login.LoginScreen
import com.were.myfirstapp.ui.theme.screens.products.AddProductScreen
import com.were.myfirstapp.ui.theme.screens.products.UpdateProductScreen
import com.were.myfirstapp.ui.theme.screens.products.ViewProductsScreen
import com.were.myfirstapp.ui.theme.screens.register.RegisterScreen
import com.were.myfirstapp.ui.theme.screens.safaricom.SafaricomScreen
import com.were.myfirstapp.ui.theme.screens.splash.SplashScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController= rememberNavController(),
               startDestination: String=ROUTE_SPLASH) {
    NavHost(modifier=modifier, navController = navController,
        startDestination = startDestination){
        composable(ROUTE_HOME){
            Homescreen(navController)
        }
        composable(ROUTE_REGISTER){
            RegisterScreen(navController)
        }
        composable(ROUTE_DASHBOARD){
            DashScreen(navController)
        }
        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUTE_INTENT) {
            Intentscreen(navController)

        }
        composable(ROUTE_SAFARICOM) {
            SafaricomScreen(navController)
        }
        composable(ROUTE_ADD_PRODUCT) {
            AddProductScreen(navController)
        }
        composable(ROUTE_VIEW_PRODUCTS) {
            ViewProductsScreen(navController)
        }
        composable("$ROUTE_UPDATE_PRODUCT/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            UpdateProductScreen(navController, id ?: "")
        }
    }
}