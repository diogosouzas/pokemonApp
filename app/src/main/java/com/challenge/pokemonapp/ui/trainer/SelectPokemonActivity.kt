package com.challenge.pokemonapp.ui.trainer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import br.com.sdiogo.challenge.pokemonfinder.R
import com.challenge.pokemonapp.PokemonFinderApp.Companion.instance
import com.challenge.pokemonapp.data.preferences.PokemonPrefs
import com.challenge.pokemonapp.helper.SpinnerPokemonTypeAdapter
import com.challenge.pokemonapp.helper.Util
import com.challenge.pokemonapp.models.Trainer
import com.challenge.pokemonapp.models.Type
import com.challenge.pokemonapp.ui.base.BaseActivity
import com.challenge.pokemonapp.ui.pokemon.search.PokemonSearchActivity
import kotlinx.android.synthetic.main.activity_select_pokemon.*
import timber.log.Timber
import javax.inject.Inject

class SelectPokemonActivity : BaseActivity(), TrainerContract.TrainerView {

    @Inject
    lateinit var presenter: TrainerContract.TrainerPresenter

    private lateinit var progressBar: ProgressBar

    private var trainerName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_pokemon)

        progressBar = findViewById(R.id.progressBar)

        imgOrder.setOnClickListener {
            onBackPressed()
        }

        setInit()
        setInjection()
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        presenter.getTypes()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish(ActivityAnimation.SLIDE_RIGHT)
    }

    override fun goToHome(type: String) {
        PokemonPrefs().setTypeFavorite(this, type)
        startActivity(Intent(this, PokemonSearchActivity::class.java), ActivityAnimation.SLIDE_LEFT)
        finish()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun onEntityError(error: String) {
        Timber.e(error)
    }

    override fun loadTypes(types: List<Type>) {
        val typeAdapter = SpinnerPokemonTypeAdapter(this, R.layout.spinner_item, types)
        typeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerTypes.adapter = typeAdapter
    }

    private fun save(name: String) {
        val typeFav = spinnerTypes.getSelectedItem() as Type
        val t = Trainer(name, typeFav.name, typeFav.thumbnailImage)
        presenter.saveTrainer(t)
    }

    private fun setInit() {
        val trainerName = intent.getStringExtra(Util.TRAINER_NAME) ?: "normal"

        imgGoToMain.setOnClickListener {
            save(trainerName)
        }
    }

    private fun setInjection() {
        instance.component.inject(this)
    }

}
