package com.challenge.pokemonapp.data.model

import io.realm.RealmObject

open class TrainerTable(
        var name: String = "",
        var typePokemonFavorite: String = "",
        var typeImageUrl: String = ""
) : RealmObject()