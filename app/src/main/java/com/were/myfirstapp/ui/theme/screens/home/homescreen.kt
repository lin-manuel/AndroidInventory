package com.were.myfirstapp.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.were.myfirstapp.R
import com.were.myfirstapp.navigation.ROUTE_LOGIN
import com.were.myfirstapp.navigation.ROUTE_REGISTER
import com.were.myfirstapp.ui.theme.neworange

@Composable
fun Homescreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFF3E0), Color(0xFFFFE0B2))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.manu),
                contentDescription = "Welcome Image",
                modifier = Modifier.size(250.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Welcome to Wetiba Mall",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = neworange,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Your one-stop destination for all your inventory management needs. Fast, reliable, and secure.",
                fontSize = 16.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = { navController.navigate(ROUTE_REGISTER) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = neworange)
            ) {
                Text("Get Started",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { navController.navigate(ROUTE_LOGIN) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(width = 2.dp)
            ) {
                Text("Login", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = neworange)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomescreenPreview() {
    Homescreen(rememberNavController())
}