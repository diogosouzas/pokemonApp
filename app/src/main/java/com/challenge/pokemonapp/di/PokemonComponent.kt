package com.challenge.pokemonapp.di

import com.challenge.pokemonapp.ui.pokemon.list.PokemonListFragment
import com.challenge.pokemonapp.ui.pokemon.search.PokemonSearchActivity
import com.challenge.pokemonapp.ui.pokemon.types.TypeFragment
import com.challenge.pokemonapp.ui.trainer.SelectPokemonActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(PokemonModule::class))
interface PokemonComponent {
    fun inject(activity: SelectPokemonActivity)

    fun inject(activity: PokemonSearchActivity)

    fun inject(fragment: TypeFragment)

    fun inject(fragment: PokemonListFragment)
}
