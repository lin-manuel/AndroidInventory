package com.were.myfirstapp.ui.theme.screens.intent

import android.content.Intent
import android.provider.MediaStore
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SimCard
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.were.myfirstapp.ui.theme.blue
import com.were.myfirstapp.ui.theme.green
import com.were.myfirstapp.ui.theme.neworange
import com.were.myfirstapp.ui.theme.newwhite
import com.were.myfirstapp.ui.theme.yellow

@Composable
fun Intentscreen(navController: NavHostController) {
    val mContext = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(newwhite)
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "System Intents",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tap any button to trigger a system action like calling, texting, or sharing.",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Intent Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IntentButton(text = "SMS", icon = Icons.Default.Sms, color = blue) {
                val smsIntent = Intent(Intent.ACTION_SENDTO)
                smsIntent.data = "smsto:0723633522".toUri()
                smsIntent.putExtra("sms_body", "Hello Erick, how was your day?")
                mContext.startActivity(smsIntent)
            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        IntentButton(text = "Call", icon = Icons.Default.Phone, color = yellow) {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = "tel:0723633522".toUri()
                mContext.startActivity(callIntent)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IntentButton(text = "Camera", icon = Icons.Default.CameraAlt, color = green) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                mContext.startActivity(cameraIntent)
            }
            IntentButton(text = "Share", icon = Icons.Default.Share, color = neworange) {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this cool content!")
                mContext.startActivity(Intent.createChooser(shareIntent, "Share"))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IntentButton(text = "Email", icon = Icons.Default.Email, color = Color.DarkGray) {
                val emailIntent = Intent(Intent.ACTION_SEND)
                emailIntent.type = "text/plain"
                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("chulabaraka12148@gmail.com"))
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, this is the email body")
                mContext.startActivity(emailIntent)
            }
            IntentButton(text = "STK", icon = Icons.Default.SimCard, color = Color(0xFF6200EE)) {
                val simToolKitLaunchIntent = mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                simToolKitLaunchIntent?.let { mContext.startActivity(it) }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Back to Home Button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = neworange)
        ) {
            Text(text = "GO BACK", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun IntentButton(text: String, icon: ImageVector, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(150.dp, 100.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(30.dp), tint = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IntentPrev() {
    Intentscreen(rememberNavController())
}