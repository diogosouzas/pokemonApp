package com.challenge.pokemonapp.ui.pokemon.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.sdiogo.challenge.pokemonfinder.R
import com.challenge.pokemonapp.helper.LoadImage
import com.challenge.pokemonapp.models.Pokemon
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_pokemon.view.*

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private var pokemons: List<Pokemon> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pokemons[position]
        holder.mContentView.text = item.name

        LoadImage.setImageUrl(holder.imgPokemon, item.thumbnailImage)
    }

    override fun getItemCount(): Int = pokemons.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.textNamePokemon
        val imgPokemon: CircleImageView = mView.imgPokemon
    }

    fun setList(pokemonList: List<Pokemon>) {
        this.pokemons = mutableListOf()
        this.pokemons = pokemonList
        notifyDataSetChanged()
    }
}
