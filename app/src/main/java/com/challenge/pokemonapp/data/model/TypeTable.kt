package com.challenge.pokemonapp.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TypeTable(
        @PrimaryKey var name: String = "",
        var thumbnailImage: String = ""
) : RealmObject()

