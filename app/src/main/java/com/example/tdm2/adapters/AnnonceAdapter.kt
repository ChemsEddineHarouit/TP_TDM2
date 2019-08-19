package com.example.tdm2.adapters

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tdm2.R
import com.example.tdm2.models.Annonce


class AnnonceAdapter( annonceList: List<Annonce>): RecyclerView.Adapter<AnnonceAdapter.ViewHolder>(){

    val annonceList = annonceList



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val annonce = annonceList[position]
        holder.titre.text = annonce.titre
        holder.description.text = annonce.description
//        val img_id = annonce.listPhotos?.first()
//        if(img_id != null)  holder?.img?.setImageResource(img_id)
//        holder?.img?.setTag(annonce.numero)
        holder.prix.text = "${annonce.prix} DA"
        holder.img.setTag(position)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.row_annonce, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return annonceList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titre = itemView.findViewById<TextView>(R.id.row_annonce_titre)
        val description = itemView.findViewById<TextView>(R.id.row_annonce_description)
        val img = itemView.findViewById<ImageView>(R.id.row_annonce_img)
        val prix = itemView.findViewById<TextView>(R.id.row_annonce_prix)
    }



}
