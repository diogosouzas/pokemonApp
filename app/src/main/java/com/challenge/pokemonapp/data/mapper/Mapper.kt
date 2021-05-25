package com.challenge.pokemonapp.data.mapper

interface Mapper<E, D> {
    fun toPresenter(model: E): D
    fun fromPresenter(table: D): E
}
