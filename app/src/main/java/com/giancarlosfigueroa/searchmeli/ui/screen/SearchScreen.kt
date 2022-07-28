package com.giancarlosfigueroa.searchmeli.ui.screen

import android.graphics.Typeface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.giancarlosfigueroa.searchmeli.R
import com.giancarlosfigueroa.searchmeli.ui.navigation.AppScreens
import com.giancarlosfigueroa.searchmeli.ui.theme.BlueMeli
import com.giancarlosfigueroa.searchmeli.ui.theme.GrayBackground
import com.giancarlosfigueroa.searchmeli.ui.theme.GrayLetter

@Composable
fun SearchScreen(navController: NavController) {
    var value by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logomini),
            contentDescription = "logo meli",
            modifier = Modifier.size(200.dp, 60.dp)
        )

        BasicTextField(
            value = value,
            onValueChange = { value = it },
            singleLine=true,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .size(300.dp, 100.dp)
                        .background(GrayBackground, RoundedCornerShape(percent = 10))
                        .padding(16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {

                    if (value.text.isEmpty()) {
                        Text("Buscar productos, marcas y más ...",
                            modifier = Modifier
                                .align(Alignment.CenterStart),
                            color = GrayLetter,
                            maxLines=1,
                            textAlign = TextAlign.Left)
                    }
                    innerTextField()  //<-- Add this

                }
            },
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            modifier = Modifier.size(200.dp,40.dp),
            onClick = {
                navController.navigate(AppScreens.ResultsScreen.route)
            }) {
            Text( text = "Buscar",
                color = BlueMeli,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp
            )

        }

    }
}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}
