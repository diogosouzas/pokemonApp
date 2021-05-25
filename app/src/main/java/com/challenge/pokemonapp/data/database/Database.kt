package com.challenge.pokemonapp.data.database

import com.challenge.pokemonapp.data.DataSource
import com.challenge.pokemonapp.data.mapper.PokemonMapper
import com.challenge.pokemonapp.data.mapper.TrainerMapper
import com.challenge.pokemonapp.data.mapper.TypeMapper
import com.challenge.pokemonapp.data.model.PokemonTable
import com.challenge.pokemonapp.data.model.TrainerTable
import com.challenge.pokemonapp.data.model.TypeTable
import com.challenge.pokemonapp.models.Pokemon
import com.challenge.pokemonapp.models.Trainer
import com.challenge.pokemonapp.models.Type
import com.vicpin.krealmextensions.*
import io.reactivex.Observable
import io.realm.Sort

class Database : DataSource {

    override fun getTypes(): Observable<List<Type>> {
        val types = TypeTable().queryAllAsFlowable()

        return types.map {
            it.map {
                TypeMapper.toPresenter(it)
            }
        }.toObservable()
    }

    override fun getPokemonsByType(type: String): Observable<List<Pokemon>> {
        val pokemons = PokemonTable().queryAsFlowable { equalTo(FIELD_TYPE_NAME, type) }

        return pokemons.map {
            it.map {
                PokemonMapper.toPresenter(it)
            }
        }.toObservable()
    }

    override fun getPokemonsOrderedByType(type: String): Observable<List<Pokemon>> {
        val pokemonsOrdered = PokemonTable().querySortedAsFlowable(FIELD_NAME, Sort.ASCENDING)
        { equalTo(FIELD_TYPE_NAME, type) }

        return pokemonsOrdered.map {
            it.map {
                PokemonMapper.toPresenter(it)
            }
        }.toObservable()
    }

    override fun getPokemonsByFilter(query: String): Observable<List<Pokemon>> {
        val pokemons = PokemonTable().querySortedAsFlowable(FIELD_NAME, Sort.ASCENDING)
        { contains(FIELD_NAME, query) }

        return pokemons.map {
            it.map {
                PokemonMapper.toPresenter(it)
            }
        }.toObservable()
    }

    override fun getPokemonDetailById(id: Int): Observable<Pokemon> {
        val pokemon = PokemonTable().queryFirst { equalTo("id", id) }?.asFlowable<PokemonTable>()

        return pokemon!!.map {
            PokemonMapper.toPresenter(it)
        }.toObservable()
    }

    override fun saveTrainer(trainer: Trainer): Boolean {
        val trainerTable = TrainerMapper.fromPresenter(trainer)
        trainerTable.save()
        return true
    }

    override fun getTypePokemonFavorite(): Observable<List<Trainer>> {
        val typeFavorites = TrainerTable().queryAllAsFlowable()

        return typeFavorites.map {
            it.map {
                TrainerMapper.toPresenter(it)
            }
        }.toObservable()
    }

    companion object {
        val FIELD_NAME = "name"
        val FIELD_TYPE_NAME = "type.name"
    }

}