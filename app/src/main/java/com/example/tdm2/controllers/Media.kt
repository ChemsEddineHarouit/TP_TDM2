package com.example.tdm2.controllers

import com.example.tdm2.models.Annonce
import android.graphics.drawable.Drawable
import java.io.InputStream
import java.net.URL


class Media private constructor(){

    private object Holder{
        val INSTANCE= Media()
    }

    companion object {
        val instance: Media by lazy { Holder.INSTANCE }

        fun loadDrawableFromUrl(url: String): Drawable? {
            try {
                val inputStream = URL(url).getContent() as InputStream
                return Drawable.createFromStream(inputStream, null)
            } catch (e: Exception) {
                println(e)
                return null
            }
        }
    }

}