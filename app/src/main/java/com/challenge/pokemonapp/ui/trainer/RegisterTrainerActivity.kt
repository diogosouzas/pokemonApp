package com.challenge.pokemonapp.ui.trainer

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import br.com.sdiogo.challenge.pokemonfinder.R
import com.challenge.pokemonapp.helper.Util
import com.challenge.pokemonapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register_trainer.*

class RegisterTrainerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_trainer)

        img_goto_select.setOnClickListener {
            goToSelectPokemonActivity()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish(ActivityAnimation.SLIDE_RIGHT)
    }

    private fun goToSelectPokemonActivity() {
        val trainerName = edt_name.text.toString()

        if (!trainerName.isEmpty()) {
            val intent = Intent(this, SelectPokemonActivity::class.java)
            intent.putExtra(Util.TRAINER_NAME, trainerName)
            startActivity(Intent(intent), ActivityAnimation.SLIDE_LEFT)
        } else {
            val error = "ERROR! The trainer name is requiered.!"
            Snackbar.make(container_trainer, error, Snackbar.LENGTH_LONG).show()
        }
    }
}
