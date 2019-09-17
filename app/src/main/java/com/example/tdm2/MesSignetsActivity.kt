package com.example.tdm2

import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tdm2.adapters.AnnonceAdapter
import com.example.tdm2.controllers.AnnonceController
import kotlinx.android.synthetic.main.activity_mes_annonces.*
import kotlinx.android.synthetic.main.activity_mes_signets.mes_annonces_list_recycler_view as mes_signets_list_recycler_view1

class MesSignetsActivity : AppCompatActivity() {
    //thread policy problem solved because I could not load img from url async
    lateinit var annonceAdapter: AnnonceAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mes_signets)

        // Set recyclerView's adapter
        mes_annonces_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val annonceController = AnnonceController.instance
        //TODO Change next line where "annonceController.getMesAnnoncesList()" must be replaced by a list of annonces from firebase (signets)
        annonceAdapter = AnnonceAdapter(annonceController.getMesAnnoncesList(this))
        mes_annonces_list_recycler_view.adapter = annonceAdapter

    }

    fun annonceClicked(view : View) {
        val intent = Intent(this, AnnonceDetailActivity::class.java)
        val id = view.getTag() as Int
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val annonceController = AnnonceController.instance
        //TODO Change next line where "annonceController.getMesAnnoncesList()" must be replaced by a list of annonces from firebase (signets)
        annonceAdapter.annonceList = annonceController.getMesAnnoncesList(this)
        annonceAdapter.notifyDataSetChanged()

    }
}
