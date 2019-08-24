package com.example.tdm2

import android.os.Bundle
//import android.support.design.widget.Snackbar
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar // this changed for androidx
import android.widget.Checkable
import androidx.appcompat.app.AppCompatActivity
import com.example.tdm2.controllers.AnnonceController
import com.example.tdm2.models.Annonce
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_annonce_detail.*

class AnnonceDetailActivity : AppCompatActivity() {

    lateinit var annonceCourante : Annonce

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annonce_detail)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val id = intent.getIntExtra("id", 5)
        val controller = AnnonceController.instance
        val annonce = controller.getAnnonceById(id)
        this.annonceCourante = annonce
        annonce_categorie.text = annonce.categorie
        annonce_contact.text = annonce.contact
        annonce_description.text = annonce.description
        annonce_localisation.text = annonce.localisation
        annonce_prix.text = annonce.prix.toString() + " DA"
        annonce_surface.text = annonce.surface.toString() + " m²"
        annonce_titre.text = annonce.titre
        annonce_type.text = annonce.type
        val annonceController = AnnonceController.instance
        annonce_offLine_switch.isChecked = annonce in annonceController.getMesAnnoncesList(this)

        annonce_offLine_switch.setOnClickListener {
            val check = it as Checkable
            val annonceController = AnnonceController.instance
            if(check.isChecked){
                annonceController.addAnnonceToMesAnnonces(annonce)
            }
            else{
                annonceController.removeAnnoncefromMesAnnonces(annonce)
            }
            annonceController.saveMesAnnonces(this)
        }
    }

    override fun onResume() {
        super.onResume()
        val annonceController = AnnonceController.instance
        annonce_offLine_switch.isChecked = annonceCourante in annonceController.getMesAnnoncesList(this)

    }

}
