package com.moragar.codechallengeyape.data.mappers

import com.google.android.gms.maps.model.LatLng
import com.moragar.codechallengeyape.data.models.IngredientDto
import com.moragar.codechallengeyape.data.models.LocationDto
import com.moragar.codechallengeyape.data.models.RecipeDto
import com.moragar.codechallengeyape.domain.entities.IngredientEntity
import com.moragar.codechallengeyape.domain.entities.RecipeEntity

internal fun RecipeDto.toEntitu() = RecipeEntity(
    id = id,
    name = name,
    image = image,
    description = description,
    location = location.toEntity(),
    ingredients = ingredients.map { it.toEntity() }
)

internal fun IngredientDto.toEntity() = IngredientEntity(
    name = name,
    quantity = quantity
)

internal fun LocationDto.toEntity() = LatLng(
    latitude, longitude
)