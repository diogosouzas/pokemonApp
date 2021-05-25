package com.challenge.pokemonapp.ui.pokemon.list

import com.challenge.pokemonapp.models.Pokemon
import com.challenge.pokemonapp.ui.base.Presenter
import com.challenge.pokemonapp.ui.base.View

interface PokemonListContract {

    interface PokemonView : View {
        fun loadPokemons(pokemons: List<Pokemon>)
    }

    interface PokemonPresenter : Presenter<PokemonView> {
        fun getPokemonsFavoriteByType(type: String)
        fun makeOrderedPokemonByName(type: String)
    }

}