package com.were.myfirstapp.ui.theme.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.were.myfirstapp.data.ProductViewModel
import com.were.myfirstapp.model.Product
import com.were.myfirstapp.navigation.ROUTE_UPDATE_PRODUCT
import com.were.myfirstapp.ui.theme.neworange

@Composable
fun ViewProductsScreen(navController: NavController, productViewModel: ProductViewModel = viewModel()) {
    val context = LocalContext.current
    val products = productViewModel.products

    LaunchedEffect(Unit) {
        productViewModel.fetchProducts(context)
    }

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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Product Inventory",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = neworange
            )
            
            Spacer(modifier = Modifier.height(20.dp))

            if (products.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = neworange)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(products) { product ->
                        ProductItem(product, navController, productViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, navController: NavController, productViewModel: ProductViewModel) {
    val context = LocalContext.current
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Image Field using AsyncImage from Coil
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = product.imageurl,
                    contentDescription = product.productname,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.productname ?: "No Name", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                Text(text = product.productcategory ?: "No Category", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(text = "Price: Ksh ${product.productprice}", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = neworange)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Qty: ${product.productquantity}", fontSize = 14.sp, color = Color.Gray)
                }
            }
            
            Column(horizontalAlignment = Alignment.End) {
                IconButton(onClick = { 
                    navController.navigate("$ROUTE_UPDATE_PRODUCT/${product.project_id}")
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color(0xFF4CAF50))
                }
                IconButton(onClick = { 
                    product.project_id?.let { productViewModel.deleteProduct(it, context) }
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewProductsScreenPreview() {
    ViewProductsScreen(rememberNavController())
}
