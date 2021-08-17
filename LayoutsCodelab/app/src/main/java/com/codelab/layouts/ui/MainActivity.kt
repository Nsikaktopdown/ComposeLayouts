/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelab.layouts.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.codelab.layouts.R
import com.codelab.layouts.tools.LayoutsCodelabTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun topBar() {

        TopAppBar(
            title = {
                Text(text = "")
            },
            elevation = 0.dp,

            navigationIcon = {
                // show drawer icon
                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_show_chart),
                        contentDescription = null
                    )
                }
            },
            actions = {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                }
            }
        )

    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreen() {
        LayoutsCodelabTheme {
            val navController = rememberNavController()
            Scaffold(
                topBar = { topBar() },
                bottomBar = { BottomNavigationBar(navController) }

            ) {
                Navigation(navController)
            }
        }
    }
}

