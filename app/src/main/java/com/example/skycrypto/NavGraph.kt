package com.example.skycrypto

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = "analytics",
        modifier = modifier
    ) {
        composable("analytics") { AnalyticsScreen() }
        composable("exchange")  { ExchangeScreen(navController) }
        composable("record")    { TransactionsScreen() }
        composable("wallet")    { AnalyticsScreen() }
        composable("wallet") { WalletScreen() }
        composable("exchange") { ExchangeScreen(navController) }
    }
}