package com.example.tdm2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.example.tdm2.adapters.AnnonceAdapter
import com.example.tdm2.adapters.WilayaAdapter
import com.example.tdm2.controllers.AnnonceController
import com.example.tdm2.controllers.WilayaController
import com.example.tdm2.enumerations.Wilaya
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mew_wilayas.*
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Checkable


class MewWilayasActivity : AppCompatActivity() {

    lateinit var wilayaAdapter: WilayaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mew_wilayas)

        wilayas_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val wilayaController = WilayaController.instance
        wilayaAdapter = WilayaAdapter(wilayaController.getMesWilayas(this))
        wilayas_recycler_view.adapter = wilayaAdapter

    }

    fun mesWilayas_save_btn(view: View){
        val wilayaController = WilayaController.instance
        wilayaController.saveMesWilayas(this)
        finish()
    }

    fun wilayaChecked(view: View){
        val matricule = view.getTag() as Int
        val wilayaController = WilayaController.instance
        val checkBox = view as Checkable
        if(checkBox.isChecked){
            wilayaController.addWilayaToMesWilayas(matricule)
        }
        else{
            wilayaController.removeWilayaToMesWilayas(matricule)
        }
    }




}