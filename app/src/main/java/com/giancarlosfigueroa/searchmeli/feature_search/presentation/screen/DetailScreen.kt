@file:OptIn(ExperimentalMaterial3Api::class)

package com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

@Composable
fun DetailScreen(navController: NavController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalle") },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Results"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            DetailProduct(name = "Moto G60s Dual SIM 128 GB azul 6 GB RAM", price = 1000000f)
        }
    )
}

@Composable
fun ImageProductDetail() {
    Image(
        painter = painterResource(R.drawable.logomini),
        contentDescription = "Image Product",
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun ListAttributes() {

    val list = (0..75).map { Text(text = "sdasd") }


}

@Composable
fun DetailProduct(name: String, price: Float) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Text(text = name, fontSize = 20.sp)

        ImageProductDetail()
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$ ${price}",
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                "20% OFF",
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        ListAttributes()
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(navController = rememberNavController())
}
