@file:OptIn(ExperimentalMaterial3Api::class)

package com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.components.LoadingShimmerEffect
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.navigation.AppScreens
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.results.ResultsViewModel

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonNull.content
import java.text.NumberFormat
import java.util.*


@Composable
fun ResultsScreen(
    navController: NavController,
    searchValue: String?,
    viewModel: ResultsViewModel = hiltViewModel()
) {
    val itemsResults = viewModel.resultsItems
    val scope = rememberCoroutineScope()
    val mContext = LocalContext.current
    //val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ResultsViewModel.UiEvent.ShowSnackbar
                -> {

                    Toast.makeText(mContext, event.message, Toast.LENGTH_LONG).show()
                    navController.navigateUp()

                }
            }
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { searchValue?.let { Text(it) } },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
                if (itemsResults.isNotEmpty()) {
                    itemsResults.forEach {
                        item() {
                            ItemCard(navController, it)
                        }
                    }
                } else {

                    repeat(8) {
                        item() { LoadingShimmerEffect() }
                    }
                }
            }


        }
    )

}


@Composable
fun ImageProductList(url: String) {
    AsyncImage(
        model = "https://${url.split("//")[1]}",
        contentDescription = "Image Product",
        modifier = Modifier.size(100.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun DescriptionProductList(
    name: String, price: Int, location: String?,
    freeShipping: Boolean, priceOriginal: Int?
) {

    val priceCurrency = NumberFormat.getCurrencyInstance(Locale("es", "CO")).format(price)
    Column() {
        Text(text = name, fontSize = 16.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = priceCurrency,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.size(8.dp))
            if (priceOriginal != null) {
                Text(
                    "${(100 * price) / priceOriginal}% OFF",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
        Spacer(modifier = Modifier.size(4.dp))
        if (freeShipping) {
            Text(
                text = "Envío gratis",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        if (location != null) {
            Text(
                text = location,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraLight,

                )
        }

    }
}

@Composable
fun ItemCard(navController: NavController, product: Product) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable {
            navController.navigate(AppScreens.DetailScreen.route + "?id=${product.id}")
        }
    ) {
        ImageProductList(product.thumbnail)
        Spacer(modifier = Modifier.size(20.dp))
        DescriptionProductList(
            name = product.title,
            price = product.price,
            product.address?.city_name,
            product.shipping.free_shipping,
            product.original_price
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    ResultsScreen(navController = rememberNavController(), "")
}
