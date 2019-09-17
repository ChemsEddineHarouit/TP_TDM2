package com.example.tdm2



import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Checkable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

import com.example.tdm2.adapters.AnnonceImageAdapter
import com.example.tdm2.controllers.AnnonceController
import com.example.tdm2.models.Annonce

import kotlinx.android.synthetic.main.activity_annonce_detail.*
import com.example.tdm2.adapters.AnnonceVideoAdapter
import com.example.tdm2.controllers.SMSController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.HashMap


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

        init_save_signet_btn(annonce)
        save_signet_btn.setOnClickListener{
            val check = it as Checkable
            if (check.isChecked){
                saveSignet(annonce)    
            }
            else {
                removeFromSignets(annonce)
            }
        }
    }

    private fun init_save_signet_btn(annonce: Annonce) {
        val uid = FirebaseAuth.getInstance().uid.toString()
        val db = FirebaseDatabase.getInstance().reference
        val key = annonce.id.toString()
        val ref = db.child("userSignets").child(uid)

        save_signet_btn.isClickable = false
        save_signet_btn.isChecked = false

        val childEventListener = object : ChildEventListener {
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                Log.d("switcher", "onChildMoved: ${p0.getValue(String::class.java)}")
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                Log.d("switcher", "onChildChanged: ${p0.key}")
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val added = p0.key.equals(key)

                save_signet_btn.isClickable = true
                if (!save_signet_btn.isChecked)
                    save_signet_btn.isChecked = added

                Log.d("switcher", "onChildAdded: $added")
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                Log.d("switcher", "onChildRemoved: ${p0.key}")
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d("switcher", "error init")
            }
        }
        ref.addChildEventListener(childEventListener)
    }

    private fun removeFromSignets(annonce: Annonce) {
        val uid = FirebaseAuth.getInstance().uid.toString()
        val db = FirebaseDatabase.getInstance().reference

        // remove link from db
        val childUpdates = HashMap<String, Any?>()
        childUpdates["/userSignets/$uid/${annonce.id}"] = null

        db.updateChildren(childUpdates)
            .addOnSuccessListener {
                Toast.makeText(this, "delete successfully $it", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "delete failed $it", Toast.LENGTH_SHORT).show()

            }
    }

    private fun saveSignet(annonce: Annonce) {
        val uid = FirebaseAuth.getInstance().uid.toString()

        val db = FirebaseDatabase.getInstance().reference

        // add link to db
        val childUpdates = HashMap<String, Any>()
        childUpdates["/userSignets/$uid/${annonce.id}"] = annonce

        db.updateChildren(childUpdates)
            .addOnSuccessListener {
                Toast.makeText(this, "added successfully $it", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "adding failed $it", Toast.LENGTH_SHORT).show()

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

    fun shareAnnonceClicked(view: View){
        val annonceLink = annonceCourante.link
        SMSController.sendSMS("Mam's", annonceLink, this)
    }
}
