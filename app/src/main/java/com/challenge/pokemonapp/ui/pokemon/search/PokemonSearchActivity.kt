package com.challenge.pokemonapp.ui.pokemon.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import br.com.sdiogo.challenge.pokemonfinder.R
import com.challenge.pokemonapp.PokemonFinderApp.Companion.instance
import com.challenge.pokemonapp.data.preferences.PokemonPrefs
import com.challenge.pokemonapp.models.Pokemon
import com.challenge.pokemonapp.models.Type
import com.challenge.pokemonapp.ui.base.BaseActivity
import com.challenge.pokemonapp.ui.pokemon.list.PokemonListFragment
import com.challenge.pokemonapp.ui.pokemon.types.TypeFragment
import timber.log.Timber
import javax.inject.Inject

class PokemonSearchActivity : BaseActivity(), PokemonSearchContract.PokemonSearchView,
    TypeFragment.onClickListener {

    @Inject
    lateinit var presenter: PokemonSearchContract.PokemonSerchPresenter

    private val pokemonListFragment = PokemonListFragment.newInstance()

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_search)

        progressBar = findViewById(R.id.progressBar)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerTypes, TypeFragment.newInstance()).commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerPokemons, pokemonListFragment).commit()
    }

    override fun onResume() {
        super.onResume()
        setInjection()
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish(ActivityAnimation.SLIDE_RIGHT)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_pokemon_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setIconifiedByDefault(true)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotBlank()) presenter.getPokemonsByFilter(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isNotBlank()) presenter.getPokemonsByFilter(query)
                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.getItemId()

        return if (id == R.id.search) {
            true
        } else super.onOptionsItemSelected(item)

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

    override fun loadPokemons(pokemons: List<Pokemon>) {
        pokemonListFragment.loadPokemons(pokemons)
    }

    override fun onClick(item: Type) {
        presenter.getPokemonsByType(item.name)
        PokemonPrefs().setTypeFavorite(this, item.name)
    }

    private fun setInjection() {
        instance.component.inject(this)
    }

}
