package com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.giancarlosfigueroa.searchmeli.R
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.navigation.AppScreens
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.search.SearchEvent
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.search.SearchViewModel
import com.giancarlosfigueroa.searchmeli.ui.theme.GrayBackground
import com.giancarlosfigueroa.searchmeli.ui.theme.GrayLetter

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Image(
            painter = painterResource(id = R.drawable.logomini),
            contentDescription = "logo meli",
            modifier = Modifier.size(200.dp, 200.dp)
        )

        BasicTextField(
            value = state.searchValue,
            onValueChange = { viewModel.onEvent(SearchEvent.EnteredSearch(it)) },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .size(300.dp, 50.dp)
                        .background(GrayBackground, RoundedCornerShape(percent = 6))
                        .padding(8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {

                    if (state.searchValue.isEmpty()) {
                        Text(
                            "Buscar productos, marcas y más ...",
                            modifier = Modifier
                                .align(Alignment.CenterStart),
                            color = GrayLetter,
                            maxLines = 1,
                            textAlign = TextAlign.Left
                        )
                    }
                    innerTextField()  //<-- Add this

                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    navController.navigate(AppScreens.ResultsScreen.route)
                },

                )
        )
        Spacer(modifier = Modifier.size(20.dp))
        FilledTonalButton(
            modifier = Modifier.size(150.dp, 40.dp),
            shape = RoundedCornerShape(6.dp),
            onClick = {
                viewModel.onEvent(SearchEvent.Search)
                navController.navigate(AppScreens.ResultsScreen.route)
                      },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                text = "Buscar",
                fontWeight = FontWeight.Medium
            )
        }


    }
}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}
