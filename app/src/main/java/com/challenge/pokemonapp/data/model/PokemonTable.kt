package com.challenge.pokemonapp.data.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey

open class PokemonTable(
        @PrimaryKey var id: Int = 0,
        var detailPageURL: String = "",
        var weight: Double = Double.MIN_VALUE,
        var number: String = "",
        var height: Double = Double.MIN_VALUE,
        var collectibles_slug: String = "",
        var featured: String = "",
        var slug: String = "",
        @Index var name: String = "",
        var thumbnailAltText: String = "",
        var thumbnailImage: String = "",
        var type: RealmList<TypeTable> = RealmList()) : RealmObject()
