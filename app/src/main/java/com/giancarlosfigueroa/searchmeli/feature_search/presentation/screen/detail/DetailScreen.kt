@file:OptIn(ExperimentalMaterial3Api::class)

package com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Attributes
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.components.LoadingShimmerEffect
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.components.LoadingShimmerEffectDetail
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.detail.DetailViewModel
import java.text.NumberFormat
import java.util.*

@Composable
fun DetailScreen(
    navController: NavController,
    id: String?,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val product = viewModel.product.value
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalle") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Results"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())

            ) {
                if (product != null) {
                    DetailProduct(product)
                }else{
                    LoadingShimmerEffectDetail()
                }
            }
        }
    )
}

@Composable
fun ImageProductDetail(url: String) {

    AsyncImage(
        model = url,
        contentDescription = "Image Product",
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun ListAttributes(attribute: List<Attributes>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        var cont = 0
        attribute.forEach {
            if (it.value_name != null) {
                val color = if (cont % 2 == 0) White else LightGray
                Row(
                    modifier = Modifier
                        .background(color = color)
                        .padding(4.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .weight(0.5f),
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Bold,
                        text = it.name
                    )
                    Spacer(Modifier.size(12.dp))
                    Text(
                        modifier = Modifier
                            .weight(0.5f),
                        textAlign = TextAlign.Left,
                        text = it.value_name
                    )
                }
                cont++
            }

        }
    }


}

@Composable
fun DetailProduct(product: Product) {

    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text(text = product.title, fontSize = 20.sp)
        val priceCurrency = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
            .format(product.price)

        product.pictures?.let { ImageProductDetail(it[0].secure_url) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = priceCurrency,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.size(8.dp))
            if (product.original_price != null) {
                Text(
                    "${(100 * product.price) / product.original_price}% OFF",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
        if (!product.attributes.isEmpty()) {
            ListAttributes(product.attributes)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(navController = rememberNavController(), "0")
}
