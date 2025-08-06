import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(val label: String, val route: String, val icon: ImageVector) {
    companion object {
        val items = listOf(
            BottomNavItem("Dashboard", "dashboard", Icons.Default.Home),
            BottomNavItem("Exchange", "exchange", Icons.Default.SwapHoriz),
            BottomNavItem("Transactions", "transactions", Icons.Default.List)
        )
    }
}
