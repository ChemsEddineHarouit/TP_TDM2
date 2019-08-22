package com.example.tdm2.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.tdm2.R
import com.example.tdm2.controllers.MediaController


class AnnonceImageAdapter(context: Context, imagesUrls: List<String>): BaseAdapter(){

    val imagesUrls = imagesUrls
    val context = context

    override fun getCount(): Int {
        return imagesUrls.size
    }

    override fun getItem(position: Int): Any {
        println("-----------------------------------------------" + position)
        return imagesUrls[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val imageView = ImageView(context)
        val img_drawable = MediaController.loadUrlIntoImg(imagesUrls[position], imageView)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setLayoutParams(AbsListView.LayoutParams(450, 450))
        return imageView
    }

//    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        val titre = itemView.findViewById<TextView>(R.id.row_annonce_titre)
//        val description = itemView.findViewById<TextView>(R.id.row_annonce_description)
//        val img = itemView.findViewById<ImageView>(R.id.row_annonce_img)
//        val prix = itemView.findViewById<TextView>(R.id.row_annonce_prix)
//    }
}