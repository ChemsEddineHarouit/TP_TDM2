package com.example.tdm2.models

import android.util.Log
import com.example.tdm2.enumerations.Wilaya
import com.prof.rssparser.Article

class Annonce(id : Int, categorie : String, type : String, localisation : String, wilaya: Wilaya,
              titre : String, surface : Int, description : String, prix : Int, contact : String,
              listPhotos : List<String>?, listVideo : List<String>?){
    val id = id
    val titre = titre
    val categorie = categorie
    val type = type
    val localisation = localisation
    val wilaya = wilaya
    val surface = surface
    val description = description
    val prix = prix
    val contact = contact
    val listPhotos = listPhotos
    val listVideo = listVideo
    override fun equals(other: Any?): Boolean {
        val annonce_2 = other as Annonce
        return (this.id == annonce_2.id)
    }

    companion object{
        fun fromArticle(a: Article, id:Int =0): Annonce {
            val prix = 15000
            val type = a.title.toString()
                .split("-")[1]
                .trim()
            val wilaya = a.title.toString()
                .split( "-")[2]
                .trim()
                .split(" ", "'")[2]

            var description = a.description.toString()
                .replace("\n", " ")
                .replace(Regex("<.*/>"), "")
                .trim()

            if (description.length > 150){
                description = description.substring(0, 150) + " ..."
            }
//            Log.d("prix", description)
            return Annonce(
                id,
                a.categories.toString(),
                type,
                a.content.toString(),
                Wilaya.Alger, // TODO  change
                a.title.toString(),
                150, // TODO  change
                description,
                prix, // TODO  change
                a.author.toString(), // TODO  change
                listOf(a.image.toString()),
                listOf() // TODO  change
            )
        }
    }
}


