package com.challenge.pokemonapp.ui.pokemon.list

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.sdiogo.challenge.pokemonfinder.R
import com.challenge.pokemonapp.PokemonFinderApp.Companion.instance
import com.challenge.pokemonapp.data.preferences.PokemonPrefs
import com.challenge.pokemonapp.models.Pokemon
import com.challenge.pokemonapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import timber.log.Timber
import javax.inject.Inject

class PokemonListFragment : BaseFragment(), PokemonListContract.PokemonView {

    @Inject
    lateinit var presenter: PokemonListContract.PokemonPresenter

    private val adapter by lazy { PokemonAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInjection()
        configureRecyclerView()
        makeOrderedPokemon()
        presenter.attachView(this)
        presenter.getPokemonsFavoriteByType(PokemonPrefs().getTypeFavorite(activity!!.baseContext))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
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
        adapter.setList(pokemons)
    }

    private fun setInjection() {
        instance.component.inject(this)
    }

    private fun configureRecyclerView() {
        pokemonRecyclerView.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        pokemonRecyclerView.adapter = adapter
    }

    private fun makeOrderedPokemon() {
        listOrder.setOnClickListener {
            presenter.makeOrderedPokemonByName(PokemonPrefs().getTypeFavorite(activity!!.baseContext))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): PokemonListFragment {
            return PokemonListFragment()
        }
    }
}
