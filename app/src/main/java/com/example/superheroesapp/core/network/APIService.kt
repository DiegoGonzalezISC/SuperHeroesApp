package com.example.superheroesapp.core.network

import com.example.superheroesapp.data.SuperHeroID
import com.example.superheroesapp.data.SuperHeroesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("search/{search_key}")
    suspend fun getSuperHeroesBySearch(
        @Path(value = "search_key", encoded = true) search: String
    ): Response<SuperHeroesResponse>

    @GET("{id}")
    suspend fun getSuperHeroesById(
        @Path(value = "id", encoded = true) id: String
    ): Response<SuperHeroID>

}