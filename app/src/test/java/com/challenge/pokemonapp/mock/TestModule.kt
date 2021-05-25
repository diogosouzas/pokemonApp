package com.challenge.pokemonapp.mock

import android.app.Application
import com.challenge.pokemonapp.di.PokemonModule
import dagger.Module

@Module
class TestModule(application: Application) : PokemonModule(application) {

}