package com.were.myfirstapp.ui.theme.screens.calc

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.were.myfirstapp.ui.theme.blue
import com.were.myfirstapp.ui.theme.green
import com.were.myfirstapp.ui.theme.neworange
import com.were.myfirstapp.ui.theme.newwhite
import com.were.myfirstapp.ui.theme.yellow


@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {

    var firstnum by remember { mutableStateOf(TextFieldValue("")) }
    var secondnum by remember { mutableStateOf(TextFieldValue("")) }
    var answer by remember { mutableStateOf("0") }

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
            text = "Modern Calculator",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Result Display Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Result",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = answer,
                        color = neworange,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Input Fields
        OutlinedTextField(
            value = firstnum,
            label = { Text(text = "First Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { firstnum = it },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = neworange,
                unfocusedBorderColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = secondnum,
            label = { Text(text = "Second Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { secondnum = it },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = neworange,
                unfocusedBorderColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Operation Buttons in a Grid-like row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalcButton(text = "+", color = blue) {
                val n1 = firstnum.text.trim().toDoubleOrNull()
                val n2 = secondnum.text.trim().toDoubleOrNull()
                answer = if (n1 != null && n2 != null) (n1 + n2).toString() else "Error"
            }
            CalcButton(text = "-", color = yellow) {
                val n1 = firstnum.text.trim().toDoubleOrNull()
                val n2 = secondnum.text.trim().toDoubleOrNull()
                answer = if (n1 != null && n2 != null) (n1 - n2).toString() else "Error"
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalcButton(text = "*", color = green) {
                val n1 = firstnum.text.trim().toDoubleOrNull()
                val n2 = secondnum.text.trim().toDoubleOrNull()
                answer = if (n1 != null && n2 != null) (n1 * n2).toString() else "Error"
            }
            CalcButton(text = "/", color = neworange) {
                val n1 = firstnum.text.trim().toDoubleOrNull()
                val n2 = secondnum.text.trim().toDoubleOrNull()
                answer = if (n1 != null && n2 != null) {
                    if (n2 != 0.0) (n1 / n2).toString() else "Div/0"
                } else "Error"
            }
        }
        
        Spacer(modifier = Modifier.height(30.dp))
        
        // Clear Button
        Button(
            onClick = {
                firstnum = TextFieldValue("")
                secondnum = TextFieldValue("")
                answer = "0"
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
        ) {
            Text(text = "CLEAR ALL", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CalcButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(80.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
    ) {
        Text(text = text, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
private fun Calcprev() {
    CalculatorScreen()
}