package com.were.myfirstapp.ui.theme.screens.dash

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.were.myfirstapp.R
import com.were.myfirstapp.data.AuthViewModel
import com.were.myfirstapp.navigation.*
import com.were.myfirstapp.ui.theme.blue
import com.were.myfirstapp.ui.theme.green
import com.were.myfirstapp.ui.theme.neworange
import com.were.myfirstapp.ui.theme.yellow

@Composable
fun DashScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()){
    val context = LocalContext.current
    // Pass the logout action as a lambda to make DashScreenContent stateless and previewable
    DashScreenContent(
        navController = navController,
        onLogout = { authViewModel.logout(context, navController) }
    )
}

@Composable
fun DashScreenContent(navController: NavController, onLogout: () -> Unit) {
    var search by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ){
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { navController.navigate(ROUTE_HOME) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AddCircle, contentDescription = "Add") },
                    label = { Text("Add") },
                    selected = false,
                    onClick = { navController.navigate(ROUTE_ADD_PRODUCT) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Products") },
                    label = { Text("Products") },
                    selected = false,
                    onClick = { navController.navigate(ROUTE_VIEW_PRODUCTS) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { /* Profile */ }
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ROUTE_ADD_PRODUCT) },
                containerColor = neworange,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Product", modifier = Modifier.size(30.dp))
            }
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFFF8F9FA))
                    .verticalScroll(rememberScrollState())
            ) {
                // Top Header Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                        .background(neworange)
                        .padding(24.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(text = "Hello there! ", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                Text(text = "Manage your inventory efficiently", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                            }
                            IconButton(
                                onClick = onLogout,
                                modifier = Modifier.background(Color.White.copy(alpha = 0.2f), CircleShape)
                            ) {
                                Icon(Icons.Default.Logout, contentDescription = "Logout", tint = Color.White)
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(20.dp))
                        
                        // Search Bar
                        OutlinedTextField(
                            value = search,
                            onValueChange = { search = it },
                            placeholder = { Text("Search products...", color = Color.White.copy(alpha = 0.6f)) },
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.White) },
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                                cursorColor = Color.White,
                                focusedTextColor = Color.White
                            )
                        )
                    }
                }

                // Quick Actions Section
                Column(modifier = Modifier.padding(top = 24.dp)) {
                    Text(
                        text = "Quick Access",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 24.dp)
                    ) {
                        DashboardProjectCard(
                            title = "View Products",
                            subtitle = "Inventory list",
                            info = "Browse all",
                            color = blue,
                            onClick = { navController.navigate(ROUTE_VIEW_PRODUCTS) }
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        DashboardProjectCard(
                            title = "Add Product",
                            subtitle = "New Arrival",
                            info = "Create item",
                            color = yellow,
                            onClick = { navController.navigate(ROUTE_ADD_PRODUCT) }
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        DashboardProjectCard(
                            title = "Safaricom",
                            subtitle = "Payments",
                            info = "Finance",
                            color = Color(0xFF00A651),
                            onClick = { navController.navigate(ROUTE_SAFARICOM) }
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        DashboardProjectCard(
                            title = "Settings",
                            subtitle = "App Config",
                            info = "Customize",
                            color = Color(0xFF673AB7),
                            onClick = { }
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                    }
                }

                // Activity Section
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Recent Activity",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ActivityItem(
                        title = "Added Smart Watch",
                        time = "2 hours ago",
                        category = "Inventory"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ActivityItem(
                        title = "Updated Laptop price",
                        time = "5 hours ago",
                        category = "Updates"
                    )
                }
            }
        }
    )
}

@Composable
fun DashboardProjectCard(title: String, subtitle: String, info: String, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(180.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Dashboard, contentDescription = null, tint = Color.White)
            }
            Column {
                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = subtitle, fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = info, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
        }
    }
}

@Composable
fun ActivityItem(title: String, time: String, category: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row (
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(neworange.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.History, contentDescription = null, tint = neworange, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text = category, fontSize = 12.sp, color = Color.Gray)
            }
            Text(text = time, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = neworange)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashScreenPreview(){
    // Use DashScreenContent in preview to avoid initializing Firebase through the ViewModel
    DashScreenContent(navController = rememberNavController(), onLogout = {})
}