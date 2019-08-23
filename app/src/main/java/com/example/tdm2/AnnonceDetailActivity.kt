package com.example.tdm2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.*
import com.example.tdm2.adapters.AnnonceImageAdapter
import com.example.tdm2.controllers.AnnonceController
import com.example.tdm2.models.Annonce

import kotlinx.android.synthetic.main.activity_annonce_detail.*
import com.example.tdm2.adapters.AnnonceVideoAdapter
import android.provider.Telephony




class AnnonceDetailActivity : AppCompatActivity(),
    AdapterView.OnItemClickListener,
    ImageFragment.OnFragmentInteractionListener{

    lateinit var annonceCourante : Annonce
    lateinit var annonceImageAdapter: AnnonceImageAdapter
    lateinit var annonceVideoAdapter: AnnonceVideoAdapter

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

        if(annonce.listVideo != null){
            annonceVideoAdapter = AnnonceVideoAdapter(this, annonce.listVideo)
            annonce_videos_grid.adapter = annonceVideoAdapter
            annonce_videos_grid.setOnItemClickListener(this)
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

    fun shareAnnonceClicked(view:View){
//        val defaultSmsPackageName =
//            Telephony.Sms.getDefaultSmsPackage(this) // Need to change the build to API 19
//        val sendIntent = Intent(Intent.ACTION_SENDTO)
//        sendIntent.type = "text/plain"
//        //sendIntent.putExtra("address", add);
////        sendIntent.data = Uri.parse("smsto:$add")
//        //TODO set the putExtra value of "sms_body" to the link of the annonce
//        sendIntent.putExtra("sms_body", "HELLO! c'est mon projet TDM2")
//        if (defaultSmsPackageName != null)
//        // Can be null in case that there is no default, then the user would be able to choose
//        // any app that support this intent.
//        {
//            sendIntent.setPackage(defaultSmsPackageName)
//        }
//        startActivity(sendIntent)
    }
}
