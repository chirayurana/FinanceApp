package com.chirayu.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chirayu.financeapp.screens.Add
import com.chirayu.financeapp.screens.Categories
import com.chirayu.financeapp.screens.Expenses
import com.chirayu.financeapp.screens.Reports
import com.chirayu.financeapp.screens.Settings
import com.chirayu.financeapp.ui.theme.FinanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceAppTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                Scaffold(

                    bottomBar = {

                            NavigationBar {
                                NavigationBarItem(
                                    selected = backStackEntry.value?.destination?.route == "expenses",
                                    onClick = { navController.navigate("expenses") },
                                    label = {
                                        Text("Expenses")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.upload_24),
                                            contentDescription = "Upload"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry?.value?.destination?.route == "reports",
                                    onClick = { navController.navigate("reports") },
                                    label = {
                                        Text("Reports")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.bar_chart),
                                            contentDescription = "Reports"
                                        )
                                    }
                                )
                                FloatingActionButton(onClick = {
                                    navController.navigate("add")},
                                    shape = CircleShape,
                                    containerColor = colorResource(id = R.color.floating_green),
                                    contentColor = Color.Black
                                ) {
                                    Icon(Icons.Filled.Add,"Floating Action Button")


                                }
                                NavigationBarItem(
                                    selected = backStackEntry?.value?.destination?.route == "add",
                                    onClick = { navController.navigate("add") },
                                    label = {
                                        Text("Add")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.add_28),
                                            contentDescription = "Add"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry?.value?.destination?.route?.startsWith("settings")?:false,
                                    onClick = { navController.navigate("settings") },
                                    label = {
                                        Text("Settings")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.settings_28),
                                            contentDescription = "Settings"
                                        )
                                    }
                                )
                            }
                    },
                    content = { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "expenses"
                        ) {
                            composable("expenses") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Expenses(navController)
                                }
                            }
                            composable("reports") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Reports(navController)
                                }
                            }
                            composable("add") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Add(navController)
                                }
                            }
                            composable("settings") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Settings(navController)
                                }
                            }
                            composable("settings/categories") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Categories(navController)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}
    @Composable
    fun Greeting(name: String) {
        Text(
            text = "Hello $name!"
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        FinanceAppTheme {
            Surface {
                Greeting("Android")
            }
        }
    }