package com.example.superheroesapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroesapp.data.Result
import com.example.superheroesapp.data.SuperHeroImpl
import kotlinx.coroutines.launch

class SuperHeroesViewModel: ViewModel() {

    private val superHeroImpl = SuperHeroImpl()

    val superHeroModel = MutableLiveData<List<Result>>()

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

}