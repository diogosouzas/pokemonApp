package com.challenge.pokemonapp.ui.main

import android.content.Intent
import android.os.Bundle
import br.com.sdiogo.challenge.pokemonfinder.R
import com.challenge.pokemonapp.PokemonFinderApp.Companion.sIsSessionActive
import com.challenge.pokemonapp.data.preferences.PokemonPrefs
import com.challenge.pokemonapp.ui.base.BaseActivity
import com.challenge.pokemonapp.ui.pokemon.search.PokemonSearchActivity
import com.challenge.pokemonapp.ui.trainer.RegisterTrainerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!sIsSessionActive) {
            sIsSessionActive = true
            goToMainActivity()
        } else {
            setupInit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish(ActivityAnimation.SLIDE_RIGHT)
    }

    private fun goToRegisterTrainer() {
        startActivity(
            Intent(this, RegisterTrainerActivity::class.java),
            ActivityAnimation.SLIDE_LEFT
        )
    }

    private fun setupInit() {
        if (!PokemonPrefs().getTypeFavorite(this).isEmpty()) {
            startActivity(
                Intent(this, PokemonSearchActivity::class.java),
                ActivityAnimation.SLIDE_LEFT
            )
            finish()
        } else {
            setContentView(R.layout.activity_main)
            btnLetsGo.setOnClickListener {
                goToRegisterTrainer()
            }
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
