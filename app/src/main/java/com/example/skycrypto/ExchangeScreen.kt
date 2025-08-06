package com.example.skycrypto

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ExchangeScreen(navController: NavHostController) {
    var from by remember { mutableStateOf("2.640") }
    val rate = 176138.80
    val receive = (from.toDoubleOrNull() ?: 0.0) * rate
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.clickable { navController.navigate("analytics") }
        ) {
            Image(
                painter = painterResource(R.drawable.img_6),
                contentDescription = "back",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate("analytics") }
            )

            Spacer(Modifier.width(10.dp))
            Text("Exchange", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF151517))
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    Text("Send",  fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Balance: 10.254 ETH", fontSize = 12.sp, color = Color(0xFFB3B3B3))
                }
                OutlinedTextField(
                    value = from,
                    onValueChange = { from = it },
                    label = { Text("ETH", color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFF2B2B2B),
                        focusedBorderColor   = Color(0xFF2B2B2B),
                        unfocusedTextColor   = Color.White,
                        focusedTextColor     = Color.White
                    )
                )
            }
        }

        /* Swap icon -------------------------------------------------------- */
        IconButton(
            onClick = { /* swap logic */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(Icons.Default.SwapVert, contentDescription = "Swap", tint = Color.White)
        }

        /* TO --------------------------------------------------------------- */
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF151517))
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    Text("Receive", fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Balance: ₹4,35,804", fontSize = 12.sp, color = Color(0xFFB3B3B3))
                }
                OutlinedTextField(
                    value = "₹ ${"%.2f".format(receive)}",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("INR", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFF2B2B2B),
                        focusedBorderColor   = Color(0xFF2B2B2B),
                        unfocusedTextColor   = Color.White,
                        focusedTextColor     = Color.White
                    )
                )
            }
        }

        /* Summary Card ----------------------------------------------------- */
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF151517))
        ) {
            Column(Modifier.padding(20.dp)) {
                SummaryRow("Exchange Rate", "1 ETH = ₹1,76,138.80")
                SummaryRow("Spread",        "0.2 %")
                SummaryRow("Gas fee",       "₹422.73")
                SummaryRow("You will receive", "₹${"%.2f".format(receive - 422.73)}")
            }
        }

        Spacer(Modifier.height(8.dp))

        /* Disclaimer at bottom -------------------------------------------- */
        Text(
            text = "The swap rate is calculated based on the current market price and may change slightly due to market volatility. The final amount will be determined at the time of transaction confirmation.",
            fontSize = 12.sp,
            color = Color(0xFFB3B3B3),
            lineHeight = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { /* mock exchange */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A84FF))
        ) {
            Text("Exchange")
        }
    }
}

@Composable
private fun SummaryRow(label: String, value: String) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label,  style = MaterialTheme.typography.bodyMedium, color = Color(0xFFB3B3B3))
        Text(value, fontWeight = FontWeight.Bold, color = Color.White)
    }
}