package com.challenge.pokemonapp.ui.trainer

import com.challenge.pokemonapp.models.Trainer
import com.challenge.pokemonapp.models.Type
import com.challenge.pokemonapp.ui.base.Presenter
import com.challenge.pokemonapp.ui.base.View

interface TrainerContract {
    interface TrainerView : View {
        fun goToHome(type: String)
        fun loadTypes(types: List<Type>)
    }

    interface TrainerPresenter : Presenter<TrainerView> {
        fun saveTrainer(trainer: Trainer)
        fun getTypes()
    }
}