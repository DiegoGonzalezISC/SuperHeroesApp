package com.example.superheroesapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroesapp.data.Result
import com.example.superheroesapp.data.SuperHeroID
import com.example.superheroesapp.data.SuperHeroImpl
import com.example.superheroesapp.domain.GetSuperHeroesPerPage
import kotlinx.coroutines.launch

class SuperHeroesViewModel: ViewModel() {

    private val superHeroImpl = SuperHeroImpl()

    val superHeroModel = MutableLiveData<List<Result>>()
    val superHeroIdModel = MutableLiveData<ArrayList<SuperHeroID?>>()
    var superHeroesIDList = ArrayList<SuperHeroID?>(emptyList())
    val currenPage = MutableLiveData<Int>()
    var loading = false
    var page = 0


    fun onCreate(searchString: String) {
        viewModelScope.launch {
            val result = superHeroImpl.callHero(searchString)

            if(result.isSuccessful) {
                val body = result.body()
                val superHeroes = body?.resultados ?: emptyList()
                superHeroModel.postValue(superHeroes)
            } else {
                Log.e("Error", "Sucedió un error" )
            }
        }
    }

    fun nextPage() {
        page ++
        loading = true
        viewModelScope.launch {
            val getSuperHeroesPerPage:GetSuperHeroesPerPage = GetSuperHeroesPerPage()
            superHeroesIDList = getSuperHeroesPerPage.getSuperHeroesList(page)
            currenPage.postValue(page)
            loading = false
        }
    }

    fun getSuperHeroes(): ArrayList<SuperHeroID?>{
        return superHeroesIDList
    }

    fun getLoadingState(): Boolean {
        return loading
    }


}