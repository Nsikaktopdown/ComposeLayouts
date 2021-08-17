package com.codelab.layouts.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codelab.layouts.ui.screens.WalletScreen
import com.codelab.layouts.ui.screens.WatchScreen


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.WatchList,
        NavigationItem.Learn,
        NavigationItem.Help,
        NavigationItem.Profile
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            WalletScreen()
        }
        composable(NavigationItem.WatchList.route) {
            WatchScreen()
        }
        composable(NavigationItem.Learn.route) {
            WalletScreen()
        }
        composable(NavigationItem.Help.route) {
            WalletScreen()
        }
        composable(NavigationItem.Profile.route) {
            WalletScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {

}