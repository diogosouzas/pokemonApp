package com.challenge.pokemonapp.ui.pokemon.types

import com.challenge.pokemonapp.models.Type
import com.challenge.pokemonapp.ui.base.Presenter
import com.challenge.pokemonapp.ui.base.View

interface TypeContract {
    interface TypeView : View {
        fun loadTypes(types: List<Type>)
    }

    interface TypePresenter : Presenter<TypeView> {
        fun getFavoriteTypes()
    }
}