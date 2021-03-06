package com.challenge.pokemonapp.ui.trainer

import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers
import com.challenge.pokemonapp.R
import com.challenge.pokemonapp.ui.pokemon.search.PokemonSearchActivity
import org.junit.Test
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.runner.AndroidJUnit4
import com.challenge.pokemonapp.models.Type
import org.hamcrest.core.IsInstanceOf
import org.hamcrest.core.StringContains
import org.junit.runner.RunWith
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId

@RunWith(AndroidJUnit4::class)
class SelectPokemonActivityTest {

    @get:Rule
    var mActivityRule = ActivityTestRule(SelectPokemonActivity::class.java, false, true)

    @Test
    fun whenActivityIsLaunched_shouldDisplayInitialState() {
        onView(withId(R.id.img_arrow)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.txt_gretting)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.txt_type_pokemon)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.spinner_types)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.img_register_trainer)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun whenSpinnerIsFilled_andClickOnNextButton_shouldOpenPokemonSearchActivity() {
        Intents.init()

        val type = "normal"

        onView(ViewMatchers.withId(R.id.spinner_types)).perform(ViewActions.click())
        onData(IsInstanceOf.instanceOf(Type::class.java)).atPosition(0).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.spinner_types)).check(ViewAssertions.matches(ViewMatchers.withSpinnerText(StringContains.containsString(type))))

        onView(ViewMatchers.withId(R.id.img_register_trainer)).perform(ViewActions.click())
        val matcher = IntentMatchers.hasComponent(PokemonSearchActivity::class.java.name)
        Intents.intended(matcher)

        Intents.release()
    }

}