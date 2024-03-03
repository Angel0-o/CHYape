package com.moragar.codechallengeyape.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.maps.model.LatLng
import com.moragar.codechallengeyape.R
import com.moragar.codechallengeyape.domain.entities.RecipeEntity
import com.moragar.codechallengeyape.presentation.navigation.Screens
import com.moragar.codechallengeyape.presentation.ui.theme.CodeChallengeYapeTheme

@Composable
fun DetailScreen(
    recipeEntity: RecipeEntity,
    navController: NavHostController
) {
    val painter = rememberAsyncImagePainter(model = recipeEntity.image)
    Column(
        modifier = Modifier.padding(16.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "searchIcon",
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .clickable { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painter,
            contentDescription = "searchIcon",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Text(
            text = recipeEntity.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = recipeEntity.description,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { navController.navigate(Screens.Map.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(25.dp),

        ) {
            Text(
                text = "Mapa",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(7.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailPreview(){
    val previewItem = RecipeEntity(
        id = "001",
        name = "Mojito",
        image = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg",
        location = LatLng(1.35, 103.87),
        description = "Refrescante cóctel que combina hojas de menta, azúcar, zumo de lima, soda, ron y hielo picado. Decorado con estilo y servido con pajita."
    )
    CodeChallengeYapeTheme {
        DetailScreen(
            recipeEntity = previewItem,
            navController = rememberNavController()
        )
    }
}