package com.example.superheroesapp.domain

import com.example.superheroesapp.data.SuperHeroID
import com.example.superheroesapp.data.SuperHeroImpl
import com.example.superheroesapp.util.Constants.Companion.PAG_ITEMS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSuperHeroesPerPage {

    private val repo = SuperHeroImpl()

    suspend fun getSuperHeroesList(pageNumber: Int): ArrayList<SuperHeroID?>  {
        val superHeroes = ArrayList<SuperHeroID?>()

        var current = pageNumber * PAG_ITEMS - (PAG_ITEMS - 1)
        var maxNumber = pageNumber * PAG_ITEMS

        for (id in current..maxNumber) {
            withContext(Dispatchers.IO) {
                val hero = repo.getSuperHeroById(id.toString())

                if (hero.isSuccessful) {
                    val body = hero.body()
                    superHeroes.add(body)
                }

            }

        }

        return superHeroes
    }
}