package com.example.superheroesapp.data

import com.google.gson.annotations.SerializedName

data class SuperHeroesResponse(
    val response: String,
    @SerializedName("results")val resultados: List<Result>,
    @SerializedName("results-for")val resultsFor: String
)