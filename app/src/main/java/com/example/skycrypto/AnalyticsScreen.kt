package com.example.skycrypto

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

/* ------------- Data ------------- */
data class Asset(
    val name: String,
    val symbol: String,
    val price: String,
    val change: String,
    val icon: Int
)

/* ------------- Screen ------------- */
@Composable
fun AnalyticsScreen() {
    val assets = remember {
        listOf(
            Asset("Bitcoin (BTC)", "", "₹75,62,502.14", "+3.2%", R.drawable.bitcoin_logo),
            Asset("Ether (ETH)", "", "₹1,79,102.50", "+3.0%", R.drawable.ethereum_logo)
        ) + List(198) {
            if (Random.nextBoolean())
                Asset("Bitcoin (BTC)", "", "₹75,62,502.14", "+3.2%", R.drawable.bitcoin_logo)
            else
                Asset("Ether (ETH)", "", "₹1,79,102.50", "+3.0%", R.drawable.ethereum_logo)
        }
    }

    val txs = remember {
        List(1000) {
            Txs(
                type = listOf("Receive", "Send").random(),
                date = "${Random.nextInt(1, 31)} Mar",
                coin = listOf("BTC", "ETH", "SOL", "ADA").random(),
                amount = "%+.6f".format(Random.nextDouble(-0.05, 0.05))
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF08080A))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 10.dp)
        ) {

            /* Curved header with icons on top */
            Box(
                modifier = Modifier
                    .width(380.dp)
                    .height(220.dp)
                    .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
            ) {
                // background image
                Image(
                    painter = painterResource(R.drawable.img_05),
                    contentDescription = "header",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // icons overlaid
                Column (Modifier.fillMaxSize())

                {
                    Spacer(Modifier.height(30.dp))

                    Row(
                        Modifier.fillMaxWidth().padding(start = 8.dp, end = 16.dp),
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

                    Spacer(Modifier.height(15.dp))
                    PortfolioHeader()
                }
            }


            Spacer(Modifier.height(10.dp))
            TimeSelector()
            Spacer(Modifier.height(12.dp))
            GraphPlaceholder()
            Spacer(Modifier.height(24.dp))

            Text("Assets", style = MaterialTheme.typography.titleMedium, color = Color.White)
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                assets.forEach { AssetCardSpec(it) }
            }

            Spacer(Modifier.height(24.dp))

            Text("Recent Transactions", style = MaterialTheme.typography.titleMedium, color = Color.White)
            Spacer(Modifier.height(8.dp))
            txs.forEach { tx ->
                TransactionItem(tx)
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = .12f))
            }
        }
    }
}

/* ------------- Card ------------- */
@Composable
private fun AssetCardSpec(asset: Asset) {
    val borderGradient = Brush.linearGradient(
        listOf(
            Color(0xFF151517).copy(alpha = 0.8f),
            Color(0xFF2B2B2B).copy(alpha = 0.8f),
            Color(0xFF151517).copy(alpha = 0.8f)
        )
    )

    Box(
        modifier = Modifier
            .width(204.dp)
            .height(118.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF0D0C0D))
            .border(
                width = 1.dp,
                brush = borderGradient,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                painter = painterResource(id = asset.icon),
                contentDescription = asset.symbol,
                tint = Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )
            Text(asset.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(asset.symbol, fontSize = 12.sp, color = Color(0xFFB3B3B3))
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(asset.price, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.White)
            Text(
                asset.change,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (asset.change.startsWith("+")) Color(0xFF4CAF50) else Color.Red
            )
        }
    }
}

@Composable
private fun PortfolioHeader() {
    Column (modifier = Modifier.padding(start = 10.dp)){
        Row (Modifier.width(350.dp).padding(start = 2.dp,end = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,)
        {
            Text("Portfolio Value >", fontSize = 15.sp, color = Color.White, fontFamily = FontFamily.Monospace)
            Image(
                painter = painterResource(R.drawable.toggle),
                contentDescription = "toggle",
                modifier = Modifier.size(80.dp)
            )
        }
        Text("₹ 1,57,342.05", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Composable
private fun TimeSelector() {
    val opts = listOf("1h", "8h", "1d", "1w", "1m", "6m", "1y")
    var sel by remember { mutableStateOf("1d") }
    Row(horizontalArrangement = Arrangement.spacedBy(2.dp), modifier = Modifier.padding(start = 7.dp)  ) {
        opts.forEach {
            FilterChip(
                selected = sel == it,
                onClick = { sel = it },
                label = { Text(it) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.Black,
                    selectedContainerColor = Color(color = 0xFF1A191B)
                )
            )
        }
    }
}

@Composable
private fun GraphPlaceholder() {
    Image(
        painter = painterResource(R.drawable.img_4),
        contentDescription = "graph",
        modifier = Modifier
            .size(width = 361.dp, height = 215.dp)
            .clip(RoundedCornerShape(12.dp))
            .alpha(1f),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun TransactionItem(txs: Txs) {
    val icon = when (txs.coin) {
        "BTC" -> R.drawable.bitcoin_logo
        "ETH" -> R.drawable.ethereum_logo
        "SOL" -> R.drawable.ic_sol
        "ADA" -> R.drawable.ic_ada
        else -> R.drawable.ic_placeholder
    }
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF151517)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
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
                Text(txs.type, fontWeight = FontWeight.Bold, color = Color.White)
                Text(txs.coin, fontSize = 12.sp, color = Color(0xFFB3B3B3))
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(txs.date, fontSize = 12.sp, color = Color(0xFFB3B3B3))
                Text(
                    text = txs.amount,
                    fontWeight = FontWeight.Bold,
                    color = if (txs.type == "Receive") Color(0xFF4CAF50) else Color.Red
                )
            }
        }
    }
}

data class Txs(
    val type: String,
    val coin: String,
    val date: String,
    val amount: String
)