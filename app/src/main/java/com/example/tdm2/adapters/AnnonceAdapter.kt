package com.example.tdm2.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.example.tdm2.R
import com.example.tdm2.controllers.AnnonceMediaController
import com.example.tdm2.models.Annonce
import kotlin.collections.ArrayList


class AnnonceAdapter( annonceList: List<Annonce>): RecyclerView.Adapter<AnnonceAdapter.ViewHolder>(), Filterable{

    var annonceList = annonceList
    var annonceSearchList = annonceList
    val annonceAllList = annonceList



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val annonce = annonceList[position]
        holder.titre.text = annonce.titre
        holder.description.text = annonce.description

        val img_url = annonce.listPhotos?.first()
        AnnonceMediaController.loadUrlIntoImg(img_url, holder.img)
        holder.prix.text = "${annonce.prix} DA"
        holder.img.setTag(annonce.id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_annonce, parent, false)
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    annonceSearchList = annonceAllList
                } else {
                    val filteredList = ArrayList<Annonce>()
                    for (annonce in annonceAllList) {
                        println("------------------------------------------------------------")
                        println(annonce.categorie.toLowerCase() + " - " + charString.toLowerCase() + " - " +  annonce.categorie.toLowerCase().equals(charString.toLowerCase()))
                        if (annonce.categorie.toLowerCase().equals(charString.toLowerCase())) {
                            filteredList.add(annonce)
                        }
                    }
                    annonceSearchList = filteredList
                }
                val filterResults = Filter.FilterResults()
                filterResults.values = annonceSearchList
                return filterResults
            }
            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                annonceSearchList = filterResults.values as List<Annonce>
                if(annonceSearchList != null) annonceList = annonceSearchList!!
                else                        annonceList = annonceAllList
                notifyDataSetChanged()
            }
        }
    }



}
