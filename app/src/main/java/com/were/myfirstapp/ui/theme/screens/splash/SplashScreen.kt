package com.were.myfirstapp.ui.theme.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.were.myfirstapp.R
import com.were.myfirstapp.data.AuthViewModel
import com.were.myfirstapp.navigation.ROUTE_DASHBOARD
import com.were.myfirstapp.navigation.ROUTE_HOME
import com.were.myfirstapp.ui.theme.neworange
import kotlinx.coroutines.delay

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()){

    LaunchedEffect(Unit) {
        delay(2000)
        if (authViewModel.isUserLoggedIn()) {
            navController.navigate(ROUTE_DASHBOARD) {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        } else {
            navController.navigate(ROUTE_HOME) {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }
    }

    SplashContent()
}

@Composable
fun SplashContent() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(neworange),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(R.drawable.manu),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Haraka Mall",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )

        Text(
            text = "Fast and Reliable",
            fontSize = 16.sp,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashContent()
}
