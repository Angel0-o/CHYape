package com.moragar.codechallengeyape.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.platform.testTag
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
import com.moragar.codechallengeyape.presentation.home.HomeViewModel.RequestState.*
import com.moragar.codechallengeyape.presentation.navigation.Screens
import com.moragar.codechallengeyape.presentation.ui.theme.CodeChallengeYapeTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    recipeList: List<RecipeEntity>,
    requestState: HomeViewModel.RequestState,
    navController: NavHostController,
    onAction: (HomeViewModel.Action) -> Unit = {}
) {
    val viewList = remember{ mutableStateOf(listOf<RecipeEntity>()) }
    val searchValue = remember{ mutableStateOf("") }
    val isSearching = remember{ mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = true){
        onAction(HomeViewModel.Action.GetRecipes)
    }
    LaunchedEffect(key1 = requestState){
        when(requestState){
            Success -> viewList.value = recipeList
            else -> {}
        }
    }
    LaunchedEffect(key1 = isSearching.value){
        if (isSearching.value){
            isSearching.value = false
            viewList.value = recipeList.filter {
                it.name.contains(searchValue.value, ignoreCase = true) ||
                        it.ingredients.any { it.name.contains(searchValue.value, ignoreCase = true) }
            }
        }
    }
    Column {
        SearchBar(
            hint = "Find your recipe",
            onText = { searchValue.value = it },
            onSearch = { isSearching.value = it },
            keyboardController = keyboardController,
            onAction = onAction
        )
        when(requestState){
            Success -> {
                if (recipeList.isNotEmpty()){
                    RecipeSection(
                        recipeList = viewList.value,
                        navController = navController,
                        onAction = onAction
                    )
                } else
                    GenericSection("No hay resultados")
            }
            Loading -> MyProgressBar()
            Error -> GenericSection("Algo salio mal, por favor intenta de nuevo")
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    hint: String = "",
    onText: (String) -> Unit = {},
    onSearch: (Boolean) -> Unit = {},
    keyboardController: SoftwareKeyboardController?,
    onAction: (HomeViewModel.Action) -> Unit = {}
) {
    var text by remember{ mutableStateOf("") }
    var isHintDisplayed by remember{ mutableStateOf(hint != "") }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .testTag("SearchBar"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = "Recipes"
        )
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onText(it)
            },
            shape = RoundedCornerShape(25.dp),
            singleLine = true,
            label = { Text(text = hint) },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused && text.isNotEmpty()
                },
            trailingIcon = {
                Image(
                    modifier = Modifier.clickable {
                        keyboardController?.hide()
                        onSearch(true)
                                                  },
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "searchIcon"
                )
            },
        )
        Image(painter = painterResource(
            id = R.drawable.ic_reload),
            contentDescription = null,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .padding(top = 16.dp)
                .clickable {
                    text = ""
                    onAction(HomeViewModel.Action.GetRecipes)
                }
        )
    }
}

@Composable
fun RecipeSection(
    recipeList: List<RecipeEntity>,
    navController: NavHostController,
    onAction: (HomeViewModel.Action) -> Unit = {}
    ){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .testTag("RecipeSection")
    ){
        items(recipeList.size){
            RecipeItem(
                recipeEntity = recipeList[it],
                navController = navController,
                onAction = onAction
            )
        }
    }
}

@Composable
fun RecipeItem(
    recipeEntity: RecipeEntity,
    navController: NavHostController,
    onAction: (HomeViewModel.Action) -> Unit = {}
){
    val painter = rememberAsyncImagePainter(model = recipeEntity.image)
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onAction(HomeViewModel.Action.SetCurrentRecipe(recipeEntity))
                navController.navigate(Screens.Detail.route)
            }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ){
            Image(
                painter = painter,
                contentDescription = "searchIcon",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Text(
                text = recipeEntity.name,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun GenericSection(message: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(8.dp)
            .testTag("GenericSection")
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_generic),
            contentDescription = "searchIcon",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Text(
            text = message,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MyProgressBar(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("MyProgressBar"),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Blue,
            modifier = Modifier.size(50.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    var previewList: List<RecipeEntity> = listOf(
        RecipeEntity(
            id = "001",
            name = "Mojito",
            image = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg",
            location = LatLng(1.35, 103.87),
            description = "Refrescante cóctel que combina hojas de menta, azúcar, zumo de lima, soda, ron y hielo picado. Decorado con estilo y servido con pajita."
        ),
        RecipeEntity(
            id = "002",
            name = "Paella",
            image = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg",
            location = LatLng(1.35, 103.87),
            description = "Refrescante cóctel que combina hojas de menta, azúcar, zumo de lima, soda, ron y hielo picado. Decorado con estilo y servido con pajita."
        )
    )
    CodeChallengeYapeTheme {
        HomeScreen(
            recipeList = previewList,
            navController = rememberNavController(),
            requestState = Success
        )
    }
}