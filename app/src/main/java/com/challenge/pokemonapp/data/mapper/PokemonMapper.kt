package com.challenge.pokemonapp.data.mapper

import com.challenge.pokemonapp.data.model.PokemonTable
import com.challenge.pokemonapp.data.model.TypeTable
import com.challenge.pokemonapp.models.Pokemon
import com.challenge.pokemonapp.models.Type
import io.realm.RealmList

object PokemonMapper : Mapper<PokemonTable, Pokemon> {

    override fun toPresenter(table: PokemonTable): Pokemon {
        return Pokemon(
                table.id,
                table.detailPageURL,
                table.weight,
                table.number,
                table.height,
                table.collectibles_slug,
                table.featured,
                table.slug,
                table.name,
                table.thumbnailAltText,
                table.thumbnailImage,
                table.type as List<Type>
        )
    }

    override fun fromPresenter(model: Pokemon): PokemonTable {
        return PokemonTable(
                model.id,
                model.detailPageURL,
                model.weight,
                model.number,
                model.height,
                model.collectibles_slug,
                model.featured,
                model.slug,
                model.name,
                model.thumbnailAltText,
                model.thumbnailImage,
                model.type as RealmList<TypeTable>
        )
    }

}