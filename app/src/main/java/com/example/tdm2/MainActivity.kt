package com.example.tdm2

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.tdm2.adapters.AnnonceAdapter
import com.example.tdm2.controllers.AnnonceController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText(R.string.title_home)
                annonces_recycler_view.visibility = View.VISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                textMessage.setText(R.string.title_dashboard)
                annonces_recycler_view.visibility = View.INVISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                textMessage.setText(R.string.title_notifications)
                annonces_recycler_view.visibility = View.INVISIBLE
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var annonceAdapter: AnnonceAdapter


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Set recyclerView's adapter
        annonces_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val annonceController = AnnonceController.instance
        annonceAdapter = AnnonceAdapter(annonceController.annonceList)
        annonces_recycler_view.adapter = annonceAdapter
    }

    fun annonceClicked(view : View) {
        val intent = Intent(this, AnnonceDetail::class.java)
        val tag = view.getTag() as Int
        intent.putExtra("position", tag)
        startActivity(intent)
    }
}
