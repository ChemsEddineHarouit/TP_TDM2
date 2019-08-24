package com.example.tdm2.controllers

import android.content.Context
import android.net.Uri
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import com.example.tdm2.R
import com.squareup.picasso.Picasso


class AnnonceMediaController private constructor(){

    private object Holder{
        val INSTANCE= AnnonceMediaController()
    }

    companion object {
        val INSTANCE: AnnonceMediaController by lazy { Holder.INSTANCE }
        fun loadUrlIntoImg(url: String?, imgView: ImageView){
            if(url == null){
                imgView.setImageResource(R.drawable.ic_house)
            }
            else{
                try {
                    Picasso.with(imgView.context)
                        .load(url)
                        .error(R.drawable.ic_house)
                        .into(imgView)
                } catch (e: Exception) {
                    println(e)
                }
            }
        }

        private fun getMedia(mediaName:String, packageName: String): Uri {
            return if (URLUtil.isValidUrl(mediaName)) {
                Uri.parse(mediaName)
            } else {
                Uri.parse(
                    "android.resource://" + packageName +
                            "/raw/" + mediaName
                )
            }
        }

        fun initializeVideoPlayer(videoView: VideoView?, bufferingTextView: TextView?, VIDEO_SAMPLE : String, packageName: String, videoPosition: Int, context: Context) {
            bufferingTextView!!.visibility = VideoView.VISIBLE
            val videoUri = AnnonceMediaController.getMedia(VIDEO_SAMPLE, packageName)
            videoView!!.setVideoURI(videoUri)
            videoView.setOnPreparedListener {
                bufferingTextView.visibility = VideoView.INVISIBLE
                if (videoPosition > 0) {
                    videoView.seekTo(videoPosition)
                } else {
                    videoView.seekTo(1)
                }
                videoView.start()
            }
            videoView.setOnCompletionListener {
                Toast.makeText(context,
                    "FIN!",
                    Toast.LENGTH_SHORT).show()
                videoView.seekTo(0)
            }
        }
    }
}