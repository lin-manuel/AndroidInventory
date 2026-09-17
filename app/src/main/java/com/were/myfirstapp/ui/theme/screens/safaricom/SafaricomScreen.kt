package com.were.myfirstapp.ui.theme.screens.safaricom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.were.myfirstapp.ui.theme.CardBackground
import com.were.myfirstapp.ui.theme.DarkBackground
import com.were.myfirstapp.ui.theme.SafaricomGreen


@Composable
fun SafaricomScreen(navController: NavController) {
    Scaffold(
        containerColor = DarkBackground,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CardBackground)
                ) {
                    Icon(Icons.Default.QrCodeScanner, contentDescription = null, tint = SafaricomGreen)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Scan to pay", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(SafaricomGreen, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "EW", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(text = "Good afternoon,", color = Color.Gray, fontSize = 12.sp)
                        Text(text = "Erick 👋", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Row {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.NotificationsNone, contentDescription = null, tint = SafaricomGreen)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = null, tint = SafaricomGreen)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Balance Cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                BalanceCard(title = "M-PESA Balance", amount = "Ksh. 113,234.56", isMain = true)
                Spacer(modifier = Modifier.width(16.dp))
                BalanceCard(title = "My Balance", amount = "Ksh. 500.00", isMain = false)
            }

            Spacer(modifier = Modifier.height(12.dp))
            // Page Indicators
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.size(16.dp, 4.dp).background(SafaricomGreen, CircleShape))
                Spacer(modifier = Modifier.width(4.dp))
                Box(modifier = Modifier.size(8.dp, 4.dp).background(Color.Gray, CircleShape))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Quick Actions
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Quick Actions", color = Color.White, fontWeight = FontWeight.Bold)
                        Text(text = "View all >", color = SafaricomGreen, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    val items = listOf(
                        ActionItem("Send Money", Icons.Default.Send),
                        ActionItem("Lipa na M-PESA", Icons.Default.Storefront),
                        ActionItem("Withdraw Money", Icons.Default.Atm),
                        ActionItem("Buy Bundles", Icons.Default.SignalCellularAlt),
                        ActionItem("International Transfers", Icons.Default.Public),
                        ActionItem("Airtime Top up", Icons.Default.PhoneAndroid),
                        ActionItem("Tunukiwa Bundles", Icons.Default.CardGiftcard),
                        ActionItem("Home Internet", Icons.Default.Home)
                    )

                    Column {
                        for (i in 0 until 2) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                for (j in 0 until 4) {
                                    val index = i * 4 + j
                                    QuickActionItem(items[index])
                                }
                            }
                            if (i == 0) Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Frequents
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Frequents", color = Color.White, fontWeight = FontWeight.Bold)
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = null, tint = Color.Gray)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(containerColor = SafaricomGreen),
                            shape = RoundedCornerShape(20.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(text = "Apps", color = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Send", color = Color.White)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Pay", color = Color.White)
                        Spacer(modifier = Modifier.weight(1f))
                        Box(contentAlignment = Alignment.TopEnd) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.Gray, CircleShape)
                            )
                            Box(
                                modifier = Modifier
                                    .background(Color.White, RoundedCornerShape(4.dp))
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                                    .offset(y = (-20).dp)
                            ) {
                                Text(text = "Need help? Talk to Zuri!", fontSize = 8.sp, color = Color.Black)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(32.dp).background(Color.Blue.copy(alpha = 0.3f), CircleShape), contentAlignment = Alignment.Center) {
                            Text(text = "KCB", color = Color.White, fontSize = 8.sp)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Kcb M-pesa", color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun BalanceCard(title: String, amount: String, isMain: Boolean) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(160.dp)
            .border(1.dp, if (isMain) SafaricomGreen else Color.Transparent, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, color = SafaricomGreen, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = amount, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.Visibility, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(SafaricomGreen)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "View Statements", color = SafaricomGreen)
            }
        }
    }
}

@Composable
fun QuickActionItem(item: ActionItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(70.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .border(1.dp, Color.Gray.copy(alpha = 0.3f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(item.icon, contentDescription = null, tint = SafaricomGreen, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.title, color = Color.White, fontSize = 10.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}

data class ActionItem(val title: String, val icon: ImageVector)

@Preview
@Composable
fun SafaricomScreenPreview() {
    SafaricomScreen(rememberNavController())
}
