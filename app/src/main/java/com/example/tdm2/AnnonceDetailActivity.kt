package com.example.tdm2

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Checkable
import com.example.tdm2.adapters.AnnonceImageAdapter
import com.example.tdm2.controllers.AnnonceController
import com.example.tdm2.models.Annonce

import kotlinx.android.synthetic.main.activity_annonce_detail.*
import android.widget.GridView





class AnnonceDetailActivity : AppCompatActivity(),
    AdapterView.OnItemClickListener,
    ImageFragment.OnFragmentInteractionListener{


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val imgUrl = annonceCourante.listPhotos?.get(position) as String
        val imageFragment = ImageFragment.newInstance(imgUrl)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, imageFragment)
            .addToBackStack(imageFragment.toString())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    lateinit var annonceCourante : Annonce
    lateinit var annonceImageAdapter: AnnonceImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annonce_detail)
//        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

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

        if(annonce.listPhotos != null){
            annonceImageAdapter = AnnonceImageAdapter(this, annonce.listPhotos)
            annonce_images_grid.adapter = annonceImageAdapter
            annonce_images_grid.setOnItemClickListener(this)
        }
    }
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        super.onResume()
        val annonceController = AnnonceController.instance
        annonce_offLine_switch.isChecked = annonceCourante in annonceController.getMesAnnoncesList(this)

    }


}
