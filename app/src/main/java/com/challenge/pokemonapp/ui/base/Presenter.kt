package com.challenge.pokemonapp.ui.base

interface Presenter<View> {
    fun attachView(t: View)
    fun detachView()
}
