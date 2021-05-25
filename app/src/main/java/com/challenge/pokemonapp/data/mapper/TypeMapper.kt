package com.challenge.pokemonapp.data.mapper

import com.challenge.pokemonapp.data.model.TypeTable
import com.challenge.pokemonapp.models.Type

object TypeMapper : Mapper<TypeTable, Type> {

    override fun toPresenter(table: TypeTable): Type {
        return Type(
                table.name,
                table.thumbnailImage
        )

    }

    override fun fromPresenter(model: Type): TypeTable {
        return TypeTable(
                model.name,
                model.thumbnailImage
        )

    }

}