package com.challenge.pokemonapp.ui.pokemon.search

import com.challenge.pokemonapp.models.Pokemon
import com.challenge.pokemonapp.ui.base.Presenter
import com.challenge.pokemonapp.ui.base.View

interface PokemonSearchContract {
    interface PokemonSearchView : View {
        fun loadPokemons(pokemons: List<Pokemon>)
    }

    interface PokemonSerchPresenter : Presenter<PokemonSearchView> {
        fun getPokemonsByFilter(name: String)
        fun getPokemonsByType(type: String)
    }
}