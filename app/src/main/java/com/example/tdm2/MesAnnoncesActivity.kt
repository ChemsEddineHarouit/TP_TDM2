package com.example.tdm2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.example.tdm2.adapters.AnnonceAdapter
import com.example.tdm2.controllers.AnnonceController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mes_annonces.*

class MesAnnoncesActivity : AppCompatActivity() {
    //thread policy problem solved because I could not load img from url async


    lateinit var annonceAdapter: AnnonceAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mes_annonces)

        // Set recyclerView's adapter
        mes_annonces_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val annonceController = AnnonceController.instance
        annonceAdapter = AnnonceAdapter(annonceController.getMesAnnoncesList(this))
        mes_annonces_list_recycler_view.adapter = annonceAdapter

    }

    fun annonceClicked(view : View) {
        val intent = Intent(this, AnnonceDetailActivity::class.java)
        val id = view.getTag() as Int
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        ArrayAdapter.createFromResource(
            this, R.array.annonce_categorie_filter_data, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
            mes_annonces_categorie_filter_spinner.adapter = adapter
        }
        mes_annonces_categorie_filter_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {
                val item = adapterView.getItemAtPosition(position)
                if(position == 0){
                    annonceAdapter.filter.filter("")
                }
                else if (item != null) {
                    annonceAdapter.filter.filter(item.toString())
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                // TODO Auto-generated method stub

            }
        };
        return true
    }

    override fun onResume() {
        super.onResume()
        val annonceController = AnnonceController.instance
        annonceAdapter.annonceList = annonceController.getMesAnnoncesList(this)
        annonceAdapter.notifyDataSetChanged()

    }
}
