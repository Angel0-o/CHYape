package com.moragar.codechallengeyape.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.moragar.codechallengeyape.presentation.home.HomeViewModel
import com.moragar.codechallengeyape.presentation.navigation.RecipeNavHost
import com.moragar.codechallengeyape.presentation.ui.theme.CodeChallengeYapeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeChallengeYapeTheme {
                val homeViewState by remember{ homeViewModel.homeModelStore }.collectAsState()

                RecipeNavHost(
                    homeViewState = homeViewState,
                    onAction = homeViewModel::onAction
                )
            }
        }
    }
}