package com.example.tdm2

import android.os.Build
import android.os.Bundle
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.tdm2.controllers.AnnonceMediaController

class VideoActivity: AppCompatActivity() {

    private var annonceVideoView:VideoView? = null
    private var bufferingTextView:TextView? = null
    lateinit var VIDEO_SAMPLE : String
    private val PLAYBACK_TIME = "play_time"

    private var currentPosition = 0

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        VIDEO_SAMPLE = intent.getStringExtra("videoUrl")
        annonceVideoView = findViewById(R.id.videoview)
        bufferingTextView = findViewById(R.id.buffering_textview)
        if (savedInstanceState != null)
        {
            currentPosition = savedInstanceState.getInt(PLAYBACK_TIME)
        }
        val controller = MediaController(this)
        controller.setMediaPlayer(annonceVideoView)
        annonceVideoView!!.setMediaController(controller)
    }


    override fun onStart() {
        super.onStart()
        AnnonceMediaController.
            initializeVideoPlayer(annonceVideoView,bufferingTextView, VIDEO_SAMPLE, packageName, currentPosition, this)
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            annonceVideoView!!.pause()
        }
    }

    override fun onStop() {
        super.onStop()
        annonceVideoView!!.stopPlayback()
    }

    override fun onSaveInstanceState(outState:Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PLAYBACK_TIME, annonceVideoView!!.currentPosition)
    }
}