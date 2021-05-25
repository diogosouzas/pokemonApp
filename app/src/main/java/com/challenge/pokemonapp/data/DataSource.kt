package com.challenge.pokemonapp.data

import com.challenge.pokemonapp.models.Pokemon
import com.challenge.pokemonapp.models.Trainer
import com.challenge.pokemonapp.models.Type
import io.reactivex.Observable

interface DataSource {
    fun getTypes(): Observable<List<Type>>

    fun getPokemonsByType(type: String): Observable<List<Pokemon>>

    fun getPokemonsOrderedByType(type: String): Observable<List<Pokemon>>

    fun getPokemonsByFilter(query: String): Observable<List<Pokemon>>

    fun getPokemonDetailById(id: Int): Observable<Pokemon>

    fun saveTrainer(trainer: Trainer): Boolean

    fun getTypePokemonFavorite(): Observable<List<Trainer>>
}