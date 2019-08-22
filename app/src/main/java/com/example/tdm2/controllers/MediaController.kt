package com.example.tdm2.controllers

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.example.tdm2.R
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.net.URL


class MediaController private constructor(){

    private object Holder{
        val INSTANCE= MediaController()
    }

    companion object {
        val INSTANCE: MediaController by lazy { Holder.INSTANCE }

        fun loadUrlIntoImg(url: String?, imgView: ImageView){
            if(url == null){
                imgView.setImageResource(R.drawable.ic_img_not_found)
            }
            else{
                try {
                    Picasso.with(imgView.context)
                        .load(url)
                        .error(R.drawable.ic_img_not_found)
                        .into(imgView)
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
    }

}