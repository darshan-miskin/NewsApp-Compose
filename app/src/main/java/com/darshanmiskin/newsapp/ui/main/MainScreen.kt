package com.darshanmiskin.newsapp.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.ui.main.model.MenuItem

enum class Destination(
    val route: String,
    val label: Int,
    val icon: Int,
    val description: Int
) {
    TOP_HEADLINES(
        "top_headlines",
        R.string.top_headlines,
        android.R.drawable.ic_menu_today,
        R.string.top_headlines
    ),
    SEARCH("search", R.string.search, android.R.drawable.ic_menu_search, R.string.search)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainMenu: List<MenuItem>, showScreen: @Composable (PaddingValues, Destination) -> Unit) {
    val navController = rememberNavController()
    val startDestination = Destination.TOP_HEADLINES
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    var showMenu by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (selectedDestination == Destination.TOP_HEADLINES.ordinal) {
                        Text(stringResource(R.string.top_headlines))
                    }
                },
                actions = {
                    if (selectedDestination == Destination.TOP_HEADLINES.ordinal) {
                        IconButton(onClick = {
                            showMenu = true
                        }) {
                            Icon(
                                painterResource(android.R.drawable.ic_menu_more),
                                contentDescription = "More"
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            mainMenu.forEach { menu ->
                                DropdownMenuItem(
                                    text = {
                                        Text(stringResource(menu.title))
                                    },
                                    onClick = {
                                        menu.listener.invoke()
                                        showMenu = false
                                    })
                            }
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            NavHost(navController = navController, startDestination = startDestination.route) {
                Destination.entries.forEach { destination ->
                    composable(destination.route) {
                        showScreen.invoke(paddingValues, destination)
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = index == selectedDestination,
                        onClick = {
                            navController.navigate(destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                painter = painterResource(destination.icon),
                                contentDescription = stringResource(destination.description)
                            )
                        },
                        label = {
                            Text(stringResource(destination.label))
                        }
                    )
                }
            }
        }
    )
}
