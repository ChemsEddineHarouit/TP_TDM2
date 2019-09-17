package com.example.tdm2

import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tdm2.adapters.AnnonceAdapter
import com.example.tdm2.controllers.AnnonceController
import com.example.tdm2.models.Annonce
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_annonce_detail.*
import kotlinx.android.synthetic.main.activity_mes_annonces.*
import kotlinx.android.synthetic.main.activity_mes_signets.mes_annonces_list_recycler_view as mes_signets_list_recycler_view1

class MesSignetsActivity : AppCompatActivity() {
    //thread policy problem solved because I could not load img from url async
    lateinit var annonceAdapter: AnnonceAdapter
    var annonceList: MutableList<Annonce?> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mes_signets)

        // Set recyclerView's adapter
        mes_annonces_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //TODO Change next line where "annonceController.getMesAnnoncesList()" must be replaced by a list of annonces from firebase (signets)
//        annonceAdapter = AnnonceAdapter(annonceList as List<Annonce>)
//        mes_annonces_list_recycler_view.adapter = annonceAdapter

        readAnnonces()

        annonceAdapter = AnnonceAdapter(annonceList as List<Annonce>)
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
        //TODO Change next line where "annonceController.getMesAnnoncesList()" must be replaced by a list of annonces from firebase (signets)
        annonceAdapter.annonceList = annonceList as List<Annonce>
        annonceAdapter.notifyDataSetChanged()
//        readAnnonces()

    }


    private fun readAnnonces() {

        val uid = FirebaseAuth.getInstance().uid.toString()
        val db = FirebaseDatabase.getInstance().reference
        val ref = db.child("userSignets").child(uid)

        annonceList = mutableListOf()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {

                    val ann = postSnapshot.getValue(Annonce::class.java)
                    annonceList.add(ann)

                    Log.d("snap", ann?.titre)
                }

                Log.d("snap", annonceList.size.toString())
                annonceAdapter = AnnonceAdapter(annonceList as List<Annonce>)
                mes_annonces_list_recycler_view.adapter = annonceAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("error", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }
}
