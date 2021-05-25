package com.challenge.pokemonapp.helper

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import br.com.sdiogo.challenge.pokemonfinder.R
import com.challenge.pokemonapp.models.Type

class SpinnerPokemonTypeAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val types: List<Type>)
    : ArrayAdapter<Type>(context, layoutResource, types) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(layoutResource, parent, false)

        val item = types[position]
        val img = view.findViewById<ImageView>(R.id.imgPokemonTypes)
        val txt = view.findViewById<TextView>(R.id.textPokemonsTypeName)

        LoadImage.setImageUrl(img, item.thumbnailImage)
        txt.text = item.name

        return view
    }

}