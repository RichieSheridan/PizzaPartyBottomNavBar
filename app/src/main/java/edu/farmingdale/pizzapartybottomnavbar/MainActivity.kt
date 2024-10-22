package edu.farmingdale.pizzapartybottomnavbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.farmingdale.pizzapartybottomnavbar.ui.theme.PizzaPartyBottomNavBarTheme
import kotlinx.coroutines.launch

import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizzaPartyBottomNavBarTheme {
                val navController: NavHostController = rememberNavController()
                var buttonsVisible by remember { mutableStateOf(true) }

                val drawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        Box(
                            Modifier
                                .offset(y=500.dp) //I pushed the drawer lower as to not interfere with the other components
                                .background(Color(0xFFF5F5F5))

                        ) {
                            DrawerContent(navController) {
                                scope.launch { drawerState.close() }
                            }
                        }
                    }
                )
                {
                    Scaffold(
                        bottomBar = {
                            if (buttonsVisible) {
                                BottomBar(
                                    navController = navController,
                                    state = buttonsVisible,
                                    modifier = Modifier
                                )
                            }
                        }
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            NavigationGraph(navController = navController) { isVisible ->
                                buttonsVisible = isVisible
                            }
                        }
                    }
                }
            }
        }
    }
}