package com.moragar.codechallengeyape.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.moragar.codechallengeyape.R
import com.moragar.codechallengeyape.domain.entities.RecipeEntity
import com.moragar.codechallengeyape.presentation.ui.theme.CodeChallengeYapeTheme

@Composable
fun MapScreen(
    recipeEntity: RecipeEntity,
    navController: NavHostController
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(recipeEntity.location, 6f)
    }
    Column{
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "searchIcon",
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .clickable { navController.popBackStack() }
        )
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ){
            Marker(
                state = MarkerState(position = recipeEntity.location),
                title = recipeEntity.name,
                snippet = "Origen"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MapPreview() {
    val previewItem = RecipeEntity(
        id = "001",
        name = "Mojito",
        image = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg",
        location = LatLng(1.35, 103.87),
        description = "Refrescante cóctel que combina hojas de menta, azúcar, zumo de lima, soda, ron y hielo picado. Decorado con estilo y servido con pajita."
    )
    CodeChallengeYapeTheme {
        MapScreen(
            recipeEntity = previewItem,
            navController = rememberNavController()
        )
    }
}