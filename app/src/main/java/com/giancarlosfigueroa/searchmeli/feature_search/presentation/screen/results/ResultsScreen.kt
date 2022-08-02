@file:OptIn(ExperimentalMaterial3Api::class)

package com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.giancarlosfigueroa.searchmeli.R
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.navigation.AppScreens


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
                    ItemCard(navController)
                }
            }
        }
    )
}


@Composable
fun ImageProductList() {
    Image(
        painter = painterResource(R.drawable.logomini),
        contentDescription = "Image Product",
        modifier = Modifier.size(100.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun DescriptionProductList(name: String, price: Float) {
    Column() {
        Text(text = name, fontSize = 16.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$ ${price}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                "20% OFF",
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Envío gratis",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.tertiary
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Bogota",
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraLight,

            )

    }
}

@Composable
fun ItemCard(navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable {
            navController.navigate(AppScreens.DetailScreen.route)
        }
    ) {
        ImageProductList()
        Spacer(modifier = Modifier.size(20.dp))
        DescriptionProductList(name = "Moto G60s Dual SIM 128 GB azul 6 GB RAM", price = 1000000f)
    }
}


@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    ResultsScreen(navController = rememberNavController())
}
