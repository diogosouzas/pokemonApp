package com.challenge.pokemonapp.di

import android.app.Application
import android.content.Context
import com.challenge.pokemonapp.data.DataSource
import com.challenge.pokemonapp.data.database.Database
import com.challenge.pokemonapp.ui.pokemon.list.PokemonListContract
import com.challenge.pokemonapp.ui.pokemon.list.PokemonListPresenter
import com.challenge.pokemonapp.ui.pokemon.search.PokemonSearchContract
import com.challenge.pokemonapp.ui.pokemon.search.PokemonSearchPresenter
import com.challenge.pokemonapp.ui.pokemon.types.TypeContract
import com.challenge.pokemonapp.ui.pokemon.types.TypePresenter
import com.challenge.pokemonapp.ui.trainer.TrainerContract
import com.challenge.pokemonapp.ui.trainer.TrainerPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class PokemonModule(private val application: Application) {

    @Provides
    @Singleton
    @ForApplication
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesDataSource(): DataSource {
        return Database()
    }

    @Provides
    @Singleton
    fun provideTrainerPresenter(dataSource: DataSource): TrainerContract.TrainerPresenter {
        return TrainerPresenter(dataSource)
    }

    @Provides
    @Singleton
    fun providePokemonSearchPresenter(dataSource: DataSource): PokemonSearchContract.PokemonSerchPresenter {
        return PokemonSearchPresenter(dataSource)
    }

    @Provides
    @Singleton
    fun providePokemonListPresenter(dataSource: DataSource): PokemonListContract.PokemonPresenter {
        return PokemonListPresenter(dataSource)
    }

    @Provides
    @Singleton
    fun provideTypePresenter(dataSource: DataSource): TypeContract.TypePresenter {
        return TypePresenter(dataSource)
    }

}