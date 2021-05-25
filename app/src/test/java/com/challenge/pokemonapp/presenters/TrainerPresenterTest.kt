package com.challenge.pokemonapp.presenters

import com.nhaarman.mockito_kotlin.verify
import com.challenge.pokemonapp.data.DataSource
import com.challenge.pokemonapp.models.Trainer
import com.challenge.pokemonapp.ui.trainer.TrainerContract
import com.challenge.pokemonapp.ui.trainer.TrainerPresenter
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.Exception

class TrainerPresenterTest {

    @Mock
    lateinit var dataSource: DataSource

    @Mock
    lateinit var view: TrainerContract.TrainerView

    lateinit var presenter: TrainerContract.TrainerPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TrainerPresenter(dataSource, Schedulers.trampoline(), Schedulers.trampoline())
        presenter.attachView(view)
    }

    @Test
    fun saveTrainer() {
        val trainer = getTrainerMock()
        val type = "normal"

        Mockito.`when`(dataSource.saveTrainer(trainer)).thenReturn(true)

        presenter.saveTrainer(trainer)

        verify(view).goToHome(type)
    }

    fun getTrainerMock(): Trainer {
        return Trainer("Dev", "normal", "ImageUrl")
    }

}