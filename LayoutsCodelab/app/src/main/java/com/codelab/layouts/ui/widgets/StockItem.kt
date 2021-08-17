package com.codelab.layouts.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.codelab.layouts.R


data class StockItem(var icon: Int, var name: String, val symbol: String, var amount: String)

@Composable
fun stockItem(stockImage: Int, name: String, symbol: String, amount: String) {
    ConstraintLayout(
        Modifier.fillMaxWidth()


    ) {
        val (iconId, symbolId, amountId) = createRefs()

        Row(Modifier.constrainAs(iconId) {
            start.linkTo(parent.start)
        }) {
            Image(
                painter = painterResource(id = stockImage),
                contentDescription = null,
                Modifier
                    .height(40.dp)
                    .width(40.dp)
            )
            Spacer(Modifier.width(20.dp))
        }
        Column(Modifier.constrainAs(symbolId) {
            start.linkTo(iconId.end)
        }) {
            Text(
                symbol,
                style = MaterialTheme.typography.body2.copy(fontSize = 18.sp)
            )
            Spacer(Modifier.height(3.dp))
            Text(
                name,
                style = MaterialTheme.typography.body2.copy(fontSize = 14.sp)
            )
        }
        Row(
            Modifier.constrainAs(amountId) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        ) {
            Text(
                amount,
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Spacer(Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_equalizer),
                contentDescription = null,
                Modifier
                    .height(20.dp)
                    .width(20.dp)
            )
        }


    }
}
