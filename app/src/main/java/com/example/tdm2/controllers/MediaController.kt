package com.example.tdm2.controllers

import android.graphics.drawable.Drawable
import java.io.InputStream
import java.net.URL


class MediaController private constructor(){

    private object Holder{
        val INSTANCE= MediaController()
    }

    companion object {
        val INSTANCE: MediaController by lazy { Holder.INSTANCE }

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