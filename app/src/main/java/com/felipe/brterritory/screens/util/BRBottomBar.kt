import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.felipe.brterritory.navigation.AppRoutes

@Composable
fun BRBottomNavBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == AppRoutes.Home.route,
            onClick = {
                navController.navigate(AppRoutes.Home.route) {
                    popUpTo(AppRoutes.Home.route) { inclusive = true }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(32.dp)
                )
            }
        )
        NavigationBarItem(
            selected = currentRoute == AppRoutes.TerritoryList.route,
            onClick = {
                navController.navigate(AppRoutes.TerritoryList.route) {
                    popUpTo(AppRoutes.TerritoryList.route) { inclusive = true }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "Territory List",
                    modifier = Modifier.size(32.dp)
                )
            }
        )
        NavigationBarItem(
            selected = currentRoute == AppRoutes.Profile.route,
            onClick = {
                if (currentRoute != AppRoutes.Profile.route) {
                    navController.navigate(AppRoutes.Profile.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.size(32.dp)
                )
            }
        )

    }
}
