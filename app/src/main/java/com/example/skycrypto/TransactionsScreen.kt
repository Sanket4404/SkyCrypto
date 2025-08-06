package com.example.skycrypto

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

/* ------------- Single Tx class ------------- */
data class Tx(
    val type: String,
    val coin: String,
    val date: String,
    val amount: String
)

@Composable
fun TransactionsScreen() {
    val txs = remember {
        List(30) {
            val types  = listOf("Receive", "Send")
            val coins  = listOf("BTC", "ETH", "SOL", "ADA")
            Txs(
                type  = types.random(),
                date  = "${Random.nextInt(1, 29)} Mar",
                coin  = coins.random(),
                amount = "%.6f".format(Random.nextDouble(-0.05, 0.05))
            )
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF08080A)),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Transactions", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(Modifier.height(8.dp))
        }
        items(txs) { tx -> TransactionCard(tx) }
    }
}

@Composable
private fun TransactionCard(txs: Txs) {
    val icon = when (txs.coin) {
        "BTC" -> R.drawable.bitcoin_logo
        "ETH" -> R.drawable.ethereum_logo
        "SOL" -> R.drawable.ic_sol
        "ADA" -> R.drawable.ic_ada
        else  -> R.drawable.ic_placeholder
    }
    val isReceive = txs.type == "Receive"

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF151517))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = txs.coin,
                tint = Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(txs.type, fontWeight = FontWeight.Bold, color = Color.White)
                Text(txs.coin, fontSize = 12.sp, color = Color(0xFFB3B3B3))
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(txs.date, fontSize = 12.sp, color = Color(0xFFB3B3B3))
                Text(
                    text = txs.amount,
                    fontWeight = FontWeight.Bold,
                    color = if (isReceive) Color(0xFF4CAF50) else Color.Red
                )
            }
        }
    }
}