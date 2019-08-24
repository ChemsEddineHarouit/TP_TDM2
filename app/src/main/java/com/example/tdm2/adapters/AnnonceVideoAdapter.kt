package com.example.tdm2.adapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.tdm2.R
import com.example.tdm2.VideoActivity


class AnnonceVideoAdapter(context: Context, videosUrls: List<String>): BaseAdapter(){

    val videosUrls = videosUrls
    val context = context

    override fun getCount(): Int {
        return videosUrls.size
    }

    override fun getItem(position: Int): Any {
        return videosUrls[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val imageView = ImageView(context)
        imageView.setImageResource(R.drawable.ic_video)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setLayoutParams(AbsListView.LayoutParams(450, 450))
        imageView.setTag("vid")
        imageView.setOnClickListener {
            val videoUrl = videosUrls.get(position)
            val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra("videoUrl", videoUrl)
            context.startActivity(intent)
        }
        return imageView
    }

}