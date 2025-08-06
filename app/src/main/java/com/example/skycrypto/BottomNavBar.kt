package com.example.skycrypto

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController) {

    val items = listOf(
        Triple("Analytics", "analytics", R.drawable.ic_analytics),
        Triple("Exchange",  "exchange",  R.drawable.ic_exchange),
        Triple("Record",    "record",    R.drawable.ic_record),
        Triple("Wallet",    "wallet",    R.drawable.ic_wallet)
    )

    val current = navController.currentBackStackEntryAsState()
        .value?.destination?.route ?: "analytics"

    /* 393×111 dp glass capsule */
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(55.5.dp))
            .background(Color(0x4142473D))
            .blur(24.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
    )

    /* Clickable icons + labels */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(111.dp)
            .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { (label, route, icon) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .clickable(enabled = current != route) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = label,
                    tint = if (current == route) Color(0xFF0A84FF) else Color(0xFF8E8E93),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = label,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (current == route) Color(0xFF0A84FF) else Color(0xFF8E8E93)
                )
            }
        }
    }
}