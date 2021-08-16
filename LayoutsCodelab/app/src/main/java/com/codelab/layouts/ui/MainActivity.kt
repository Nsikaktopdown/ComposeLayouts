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
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.codelab.layouts.Chip
import com.codelab.layouts.R
import com.codelab.layouts.tools.LayoutsCodelabTheme

class MainActivity : AppCompatActivity() {

    val stockGroup = listOf(
        "All-Stocks",
        "Technology",
        "Medical",
        "Entertainment"
    )
    val colors = listOf(
        Color(0xFFDB5DBD),
        Color(0xFF025889),
        Color(0xFFAB75FB),
        Color(0xFFED1D23)

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotographerCardPreview()
        }
    }


    @Composable
    fun PhotographerCard(modifier: Modifier = Modifier) {
        Row(
            modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
                .clickable(onClick = {})
                .padding(16.dp)
        ) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                // Image goes here
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text("Alfred Sisley", fontWeight = FontWeight.Bold)
                // LocalContentAlpha is defining opacity level of its children
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text("3 minutes ago", style = MaterialTheme.typography.body2)
                }
            }
        }
    }

    @Composable
    fun profileList(modifier: Modifier = Modifier) {
        val scrollState = rememberLazyListState()
        LazyColumn(state = scrollState) {
            items(10) {
                PhotographerCard(modifier)
            }
        }
    }

    @Composable
    private fun topBar() {

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
                    Toast.makeText(
                        this@MainActivity,
                        "This is Search",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                }
            }
        )

    }

    @Composable
    fun Chip(modifier: Modifier = Modifier, text: String, color: Color) {

        Card(
            modifier = modifier,
            shape = RoundedCornerShape(4.dp),
            backgroundColor = color.copy(alpha = 0.5f)

        ) {
            Row(
                modifier = Modifier.padding(start = 10.dp, top = 6.dp, end = 10.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp, 16.dp)
                        .background(color = color)
                )
                Spacer(Modifier.width(4.dp))
                Text(text = text, style = MaterialTheme.typography.body2)
            }
        }
    }

    @Composable
    fun chipList() {
        val scrollState = rememberLazyListState()
        LazyRow(state = scrollState) {
            items(count = stockGroup.size) { index ->
                Chip(
                    modifier = Modifier.padding(8.dp),
                    text = stockGroup[index],
                    color = colors[index]
                )
            }
        }

    }

    @Preview
    @Composable
    fun PhotographerCardPreview() {

        LayoutsCodelabTheme {
            Scaffold(
                topBar = {
                    topBar()
                }
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    WalletView()
                    Column(modifier = Modifier.padding(15.dp)) {
                        chipList()
                        Spacer(Modifier.height(20.dp))
                        Text(
                            "My Stocks",
                            style = MaterialTheme.typography.h1.copy(fontSize = 18.sp)
                        )
                        Spacer(Modifier.height(20.dp))
                        ConstraintLayout(
                            Modifier.fillMaxWidth()


                        ) {
                            val (icon, symbol, amount) = createRefs()

                            Row(Modifier.constrainAs(icon) {
                                start.linkTo(parent.start)
                            }) {
                                Image(
                                    painter = painterResource(id = R.drawable.facebook),
                                    contentDescription = null
                                )
                                Spacer(Modifier.width(20.dp))
                            }
                            Column(Modifier.constrainAs(symbol) {
                                start.linkTo(icon.end)
                            }) {
                                Text(
                                    "FB",
                                    style = MaterialTheme.typography.body2.copy(fontSize = 18.sp)
                                )
                                Spacer(Modifier.height(5.dp))
                                Text(
                                    "Facebook",
                                    style = MaterialTheme.typography.body2.copy(fontSize = 14.sp)
                                )
                            }
                            Row(
                                Modifier.constrainAs(amount) {
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                            ) {
                                Text(
                                    "$40.86",
                                    style = MaterialTheme.typography.body2.copy(fontSize = 18.sp)
                                )
                                Spacer(Modifier.width(10.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.ic_equalizer),
                                    contentDescription = null
                                )
                            }

                        }
                    }


                }

            }

        }
    }
}

