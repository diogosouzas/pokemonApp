package com.challenge.pokemonapp.helper

import android.widget.ImageView
import com.bumptech.glide.Glide

object LoadImage {
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}
