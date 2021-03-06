package com.challenge.pokemonapp.ui.trainer

import com.challenge.pokemonapp.data.DataSource
import com.challenge.pokemonapp.models.Trainer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class TrainerPresenter @Inject constructor(
    var dataSource: DataSource,
    val subscriberScheduler: Scheduler = Schedulers.io(),
    val observerScheduler: Scheduler = AndroidSchedulers.mainThread()
) : TrainerContract.TrainerPresenter {

    private lateinit var view: TrainerContract.TrainerView

    private val subscriptions = CompositeDisposable()

    override fun attachView(t: TrainerContract.TrainerView) {
        this.view = t
    }

    override fun detachView() {
        subscriptions.clear()
    }

    override fun getTypes() {
        val subscr = dataSource.getTypes()
            .subscribeOn(subscriberScheduler)
            .observeOn(observerScheduler)
            .subscribe(
                { types ->
                    view.loadTypes(types)
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

    override fun saveTrainer(trainer: Trainer) {
        dataSource.saveTrainer(trainer)

        view.goToHome(trainer.typePokemon)
    }
}