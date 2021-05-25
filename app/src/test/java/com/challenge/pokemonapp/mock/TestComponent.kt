package com.challenge.pokemonapp.mock

import com.challenge.pokemonapp.di.PokemonComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TestModule::class))
interface TestComponent : PokemonComponent {

}