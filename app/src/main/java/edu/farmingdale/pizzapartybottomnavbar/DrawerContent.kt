package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun DrawerContent(navController: NavHostController, onDestinationClicked: () -> Unit) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Column(modifier = Modifier.padding(16.dp)) {
        //Drawer item for Pizza
        DrawerItem(
            icon = Icons.Default.ShoppingCart,
            label = "Pizza Order",
            selected = currentRoute == BottomNavigationItems.PizzaScreen.route,
            onClick = {
                navController.navigate(BottomNavigationItems.PizzaScreen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                onDestinationClicked()
            }
        )
        Divider()

        //Drawer item for GPA
        DrawerItem(
            icon = Icons.Default.CheckCircle,
            label = "GPA App",
            selected = currentRoute == BottomNavigationItems.GpaAppScreen.route,
            onClick = {
                navController.navigate(BottomNavigationItems.GpaAppScreen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                onDestinationClicked()
            }
        )
        Divider()

        //Drawer item for 3rd Screen
        DrawerItem(
            icon = Icons.Default.AccountCircle,
            label = "Screen 3",
            selected = currentRoute == BottomNavigationItems.Screen3.route,
            onClick = {
                navController.navigate(BottomNavigationItems.Screen3.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                onDestinationClicked()
            }
        )
    }
}

@Composable
fun DrawerItem(icon: ImageVector, label: String, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (selected) Color(0xFFBB86FC) else Color.Transparent //Highlight selected
    val contentColor = if (selected) Color.White else Color.Black //Change txt color if selected

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = label,
            color = contentColor,
            fontSize = 18.sp
        )
    }
}
