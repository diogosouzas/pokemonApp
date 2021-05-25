package com.challenge.pokemonapp.ui.base

interface View {
    fun showProgress()
    fun hideProgress()
    fun onEntityError(error: String)
}
