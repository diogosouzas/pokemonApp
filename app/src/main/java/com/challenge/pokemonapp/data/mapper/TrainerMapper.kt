package com.challenge.pokemonapp.data.mapper

import com.challenge.pokemonapp.data.model.TrainerTable
import com.challenge.pokemonapp.models.Trainer

object TrainerMapper : Mapper<TrainerTable, Trainer> {

    override fun toPresenter(table: TrainerTable): Trainer {
        return Trainer(
                table.name,
                table.typePokemonFavorite,
                table.typeImageUrl
        )

    }

    override fun fromPresenter(model: Trainer): TrainerTable {
        return TrainerTable(
                model.name,
                model.typePokemon,
                model.typeImageUrl
        )

    }

}