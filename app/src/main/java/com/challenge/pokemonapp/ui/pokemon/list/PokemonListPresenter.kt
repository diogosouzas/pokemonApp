package com.challenge.pokemonapp.ui.pokemon.list

import com.challenge.pokemonapp.data.DataSource
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PokemonListPresenter @Inject constructor(
    var dataSource: DataSource,
    val subscriberScheduler: Scheduler = Schedulers.io(),
    val observerScheduler: Scheduler = AndroidSchedulers.mainThread()
) : PokemonListContract.PokemonPresenter {

    private lateinit var view: PokemonListContract.PokemonView

    private val subscriptions = CompositeDisposable()

    override fun attachView(t: PokemonListContract.PokemonView) {
        view = t
    }

    override fun detachView() {
        subscriptions.clear()
    }

    override fun getPokemonsFavoriteByType(type: String) {
        view.showProgress()
        val subscr = dataSource.getPokemonsByType(type)
            .subscribeOn(subscriberScheduler)
            .observeOn(observerScheduler)
            .subscribe(
                { pokemons ->
                    view.loadPokemons(pokemons)
                    view.hideProgress()
                },
                { throwable ->
                    Timber.e(throwable)
                    view.hideProgress()
                },
                {
                    view.hideProgress()
                }
            )

        subscriptions.add(subscr)
    }

    override fun makeOrderedPokemonByName(type: String) {
        view.showProgress()
        val subscr = dataSource.getPokemonsOrderedByType(type)
            .subscribeOn(subscriberScheduler)
            .observeOn(observerScheduler)
            .subscribe(
                { pokemons ->
                    view.loadPokemons(pokemons)
                    view.hideProgress()
                },
                { throwable ->
                    Timber.e(throwable)
                    view.hideProgress()
                },
                {
                    view.hideProgress()
                }
            )

        subscriptions.add(subscr)
    }

}