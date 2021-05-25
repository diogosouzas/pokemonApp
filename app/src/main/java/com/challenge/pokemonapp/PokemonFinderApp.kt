package com.challenge.pokemonapp

import android.app.Application
import br.com.sdiogo.challenge.pokemonfinder.BuildConfig
import com.challenge.pokemonapp.data.database.PokemonPopulate
import com.challenge.pokemonapp.data.preferences.PokemonPrefs
import com.challenge.pokemonapp.di.DaggerPokemonComponent
import com.challenge.pokemonapp.di.PokemonComponent
import com.challenge.pokemonapp.di.PokemonModule
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

class PokemonFinderApp : Application() {

    lateinit var component: PokemonComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        initGraph()

        initPref()

        initRealm()

        makeSeedData()

        initDebug()
    }

    private fun initGraph() {
        component = DaggerPokemonComponent.builder()
                .pokemonModule(PokemonModule(instance))
                .build()
    }

    private fun initRealm() {
        Realm.init(this)
        RealmConfiguration.Builder()
                .name(POKEMON_DB)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded().build()
    }

    private fun initPref() {
        PokemonPrefs().prefs(this)
    }

    private fun makeSeedData() {
        if (PokemonPrefs().getInitDate(this).isEmpty()) {
            val realm = Realm.getDefaultInstance()
            val seed = PokemonPopulate(this, realm)
            seed.populateData()
        }
    }

    private fun initDebug() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var instance: PokemonFinderApp private set
        var sIsSessionActive: Boolean = false
        var POKEMON_DB = "pokemonsapp-db"
    }

}