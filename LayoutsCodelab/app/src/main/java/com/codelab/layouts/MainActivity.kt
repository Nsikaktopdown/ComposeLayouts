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

package com.codelab.layouts

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.codelab.layouts.ui.LayoutsCodelabTheme

class MainActivity : AppCompatActivity() {
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
    fun profileList(modifier: Modifier = Modifier){
        val scrollState = rememberLazyListState()
        LazyColumn(state = scrollState ){
            items(10){
                PhotographerCard(modifier)
            }
        }
    }


    @Composable
    fun balanceLayout(){
        ConstraintLayout(
        ) {

            val (currency, balance, trailing) = createRefs()


            Text("$", Modifier.constrainAs(currency) {
                top.linkTo(parent.top, margin = 16.dp)

            })
            Text("0", Modifier.constrainAs(balance) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(currency.end)
            })
            Text(".0", Modifier.constrainAs(trailing) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(balance.end)
            })

        }
    }
    @Preview
    @Composable
    fun PhotographerCardPreview() {
        LayoutsCodelabTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Profile")
                        }

                    )
                }
            ) {
                 //   innerPadding ->
               Column(
                   modifier = Modifier.fillMaxSize(),
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   balanceLayout()
                   Row(){
                       Text("$0.00")
                       Spacer(modifier = Modifier.width(5.dp))
                       Text("(0.00%)")
                       Spacer(modifier = Modifier.width(5.dp))
                       Text("TODAY")
                   }
                   Text("PORTFOLIO VALUE")
                  // profileList(Modifier.padding(innerPadding))
               }

            }

        }
    }
}

