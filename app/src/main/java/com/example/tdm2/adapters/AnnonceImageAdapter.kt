package com.example.tdm2.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.tdm2.controllers.AnnonceMediaController


class AnnonceImageAdapter(context: Context, imagesUrls: List<String>): BaseAdapter(){

    val imagesUrls = imagesUrls
    val context = context

    override fun getCount(): Int {
        return imagesUrls.size
    }

    override fun getItem(position: Int): Any {
        return imagesUrls[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val imageView = ImageView(context)
        AnnonceMediaController.loadUrlIntoImg(imagesUrls[position], imageView)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setLayoutParams(AbsListView.LayoutParams(450, 450))
        imageView.setTag("img")
        return imageView
    }

}