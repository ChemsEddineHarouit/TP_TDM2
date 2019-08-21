package com.example.tdm2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.example.tdm2.adapters.AnnonceAdapter
import com.example.tdm2.controllers.AnnonceController
import kotlinx.android.synthetic.main.activity_main.*
import android.os.StrictMode
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import com.example.tdm2.controllers.WilayaController
import kotlinx.android.synthetic.main.profile_view_layout.*


class MainActivity : AppCompatActivity() {

    lateinit var annonceAdapter: AnnonceAdapter

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                annonce_list_view.visibility = View.VISIBLE
                profile_view.visibility = View.GONE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                annonce_list_view.visibility = View.GONE
                profile_view.visibility = View.GONE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                annonce_list_view.visibility = View.GONE
                profile_view.visibility = View.VISIBLE
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        //thread policy problem solved because I could not load img from url async
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Set recyclerView's adapter
        annonce_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val annonceController = AnnonceController.instance
        annonceAdapter = AnnonceAdapter(annonceController.annonceList)
        annonce_list_recycler_view.adapter = annonceAdapter


        //Set mon profile
        set_monProfileData(this)
    }

    private fun set_monProfileData(context: Context) {
        //TODO the set nom, prenom, photo ....etc
        //set mes wilayas
        val wilayaController = WilayaController.instance
        var mesWilayasString = "Mes Wilayas: \n"
        for (wilaya in wilayaController.loadMesWilayas(context)){
            mesWilayasString += "" + wilaya + "   "
        }
        compte_mes_wilayas.setText(mesWilayasString)
    }

    override fun onResume() {
        super.onResume()
        set_monProfileData(this)
    }

    fun annonceClicked(view : View) {
        val intent = Intent(this, AnnonceDetailActivity::class.java)
        val id = view.getTag() as Int
        intent.putExtra("id", id)
        startActivity(intent)
    }

    fun modifierMesWilayasBtnClicked(view : View){
        val intent = Intent(this, MewWilayasActivity::class.java)
        startActivity(intent)
    }

    fun mesAnnoncesBtnClicked(view: View){
        val intent = Intent(this, MesAnnoncesActivity::class.java)
        startActivity(intent)
    }



}
