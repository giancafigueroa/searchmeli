@file:OptIn(ExperimentalMaterial3Api::class)

package com.giancarlosfigueroa.searchmeli.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.giancarlosfigueroa.searchmeli.R


@Composable
fun ResultsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Motorola") },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Home"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val list = (0..75).map { it.toString() }
                items(count = list.size) {
                    ItemCard()
                }
            }
        }
    )
}


@Composable
fun ImageProductList(){
    Image(
        painter = painterResource(R.drawable.logomini),
        contentDescription = "Image Product",
        modifier = Modifier.size(120.dp),
        contentScale = ContentScale.Fit
    )
}
@Composable
fun DescriptionProductList(name:String,price:Float){
    Column() {
        Text(text = name)
        Text(text = "$${price}")
    }
}

@Composable
fun ItemCard(){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)){
        ImageProductList()
        Spacer(modifier = Modifier.size(20.dp))
        DescriptionProductList(name = "Motrola", price = 1000000f)
    }
}


@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    ResultsScreen(navController = rememberNavController())
}
