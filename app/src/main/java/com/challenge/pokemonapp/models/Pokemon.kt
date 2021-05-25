package com.challenge.pokemonapp.models

data class Pokemon(
        var id: Int,
        var detailPageURL: String,
        var weight: Double,
        var number: String,
        var height: Double,
        var collectibles_slug: String,
        var featured: String,
        var slug: String,
        var name: String,
        var thumbnailAltText: String,
        var thumbnailImage: String,
        var type: List<Type>
)