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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WalletScreen() {

    val transactions = remember {
        listOf(
            Txs("Receive", "BTC", "20 March", "+0.002126"),
            Txs("Send",    "ETH", "19 March", "-0.003126"),
            Txs("Send",    "LTC", "18 March", "+0.02126")
        )
    }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF08080A))
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        /* ---------- Top-bar icons ---------- */
        item {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

            ) {
                Image(
                    painter = painterResource(R.drawable.container),
                    contentDescription = "container",
                    modifier = Modifier.size(45.dp)
                )
                Spacer(Modifier.width(12.dp))
                Image(
                    painter = painterResource(R.drawable.noti),
                    contentDescription = "notification",
                    modifier = Modifier.size(45.dp)
                )
            }
        }

        /* ---------- Unified glass card with banner background ---------- */
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0x191C1C1E))
            ) {
                // background image
                Image(
                    painter = painterResource(R.drawable.img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                // texts on top
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("INR", fontSize = 14.sp, color = Color.White)
                    Text("1,57,342.05", fontSize = 36.sp,
                        fontWeight = FontWeight.Bold, color = Color.White)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("1,342", fontSize = 18.sp,
                            fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(Modifier.width(12.dp))
                        Text("+4.6 %", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
                    }
                }
            }
        }

        /* ---------- Icon bar ---------- */
        item {
            Row(
                Modifier.fillMaxWidth().padding(start = 50.dp,end = 50.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_5),
                    contentDescription = "up",
                    modifier = Modifier.size(50.dp)
                )
                Image(
                    painter = painterResource(R.drawable.img_1),
                    contentDescription = "add",
                    modifier = Modifier.size(50.dp)
                )
                Image(
                    painter = painterResource(R.drawable.img_2),
                    contentDescription = "down",
                    modifier = Modifier.size(50.dp)
                )
            }
        }

        /* ---------- Transactions header ---------- */
        item {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Transactions",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Last 4 days",
                    fontSize = 12.sp,
                    color = Color(0xFFB3B3B3)
                )
            }
        }

        items(transactions) { TransactionRow(it) }
    }
}

@Composable
private fun IconButton(icon: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            tint = Color(0xFF8E8E93),
            modifier = Modifier.size(24.dp)
        )
        if (label.isNotEmpty()) {
            Text(
                text = label,
                fontSize = 11.sp,
                color = Color(0xFF8E8E93)
            )
        }
    }
}

@Composable
private fun TransactionRow(txs: Txs) {
    val icon = when (txs.coin) {
        "BTC" -> R.drawable.bitcoin_logo
        "ETH" -> R.drawable.ethereum_logo
        "LTC" -> R.drawable.ic_ltc
        else  -> R.drawable.ic_placeholder
    }
    Card(
        shape = RoundedCornerShape(12.dp),
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
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = txs.type,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = txs.coin,
                    fontSize = 12.sp,
                    color = Color(0xFFB3B3B3)
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = txs.date,
                    fontSize = 12.sp,
                    color = Color(0xFFB3B3B3)
                )
                Text(
                    text = txs.amount,
                    fontWeight = FontWeight.Bold,
                    color = if (txs.type == "Receive") Color(0xFF4CAF50) else Color.Red
                )
            }
        }
    }
}

data class Txx(
    val type: String,
    val coin: String,
    val date: String,
    val amount: String
)