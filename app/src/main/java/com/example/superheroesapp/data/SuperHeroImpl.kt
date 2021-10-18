package com.example.superheroesapp.data

import com.example.superheroesapp.core.helpers.RetrofitHelper
import com.example.superheroesapp.core.network.APIService
import retrofit2.Response

class SuperHeroImpl {

    suspend fun callHero(searchStr: String): Response<SuperHeroesResponse> {
        return RetrofitHelper.getRetrofit().create(APIService::class.java)
            .getSuperHeroesBySearch(searchStr)
    }

}