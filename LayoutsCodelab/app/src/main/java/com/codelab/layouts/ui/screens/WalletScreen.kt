package com.codelab.layouts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelab.layouts.R
import com.codelab.layouts.ui.widgets.Chip
import com.codelab.layouts.ui.widgets.StockItem
import com.codelab.layouts.ui.widgets.WalletView
import com.codelab.layouts.ui.widgets.stockItem

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
var dummyStocks = listOf(
    StockItem(R.drawable.facebook, "Facebook", "FB", "$30.78"),
    StockItem(R.drawable.slack, "Slack Technologies", "SLK", "$100.78"),
    StockItem(R.drawable.google, "Google", "GO", "$110.78"),
    StockItem(R.drawable.tesla, "Tesla", "TSLA", "$500.78"),
    StockItem(R.drawable.facebook, "Facebook", "FB", "$30.78"),
    StockItem(R.drawable.slack, "Slack Technologies", "SLK", "$100.78"),
    StockItem(R.drawable.google, "Google", "GO", "$110.78"),
    StockItem(R.drawable.tesla, "Tesla", "TSLA", "$500.78")
)

@Composable
fun stockList() {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(count = dummyStocks.size) { index ->

            stockItem(
                stockImage = dummyStocks[index].icon,
                name = dummyStocks[index].name,
                symbol = dummyStocks[index].symbol,
                amount = dummyStocks[index].amount
            )
            Spacer(Modifier.height(10.dp))
            Divider(color = Color.LightGray)
            Spacer(Modifier.height(10.dp))
        }
    }

}



@Composable
fun chipList() {
    val scrollState = rememberLazyListState()
    LazyRow(state = scrollState) {
        items(count = stockGroup.size) { index ->
            Chip(
                text = stockGroup[index],
                color = colors[index]
            )
        }
    }

}

@Composable
fun stockView() {
    Column(modifier = Modifier.padding(15.dp)) {
        chipList()
        Spacer(Modifier.height(20.dp))
        Text(
            "My Stocks",
            style = MaterialTheme.typography.h1.copy(fontSize = 18.sp)
        )
        Spacer(Modifier.height(20.dp))
        stockList()

    }

}

@Preview(showBackground = true)
@Composable
fun WalletScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        WalletView()
        stockView()


    }
}
