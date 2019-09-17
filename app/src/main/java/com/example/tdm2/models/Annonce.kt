package com.example.tdm2.models

import android.util.Log
import com.example.tdm2.enumerations.Wilaya
import com.prof.rssparser.Article

class Annonce(id : Int, categorie : String, type : String, localisation : String, wilaya: Wilaya,
              titre : String, surface : Int, description : String, prix : Int, contact : String,
              listPhotos : List<String>?, listVideo : List<String>?, link: String){
    val id = id
    val link = link
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

    constructor(): this(-1, "", "", "", Wilaya.Alger, "", 0, "", 0, "", null, null, "")

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
            var imageList : List<String>? = null
            if (a.image != null)
                imageList = listOf(a.image.toString())

            // default value: vente
            var categorie = "Vente"
            if (description.contains("location",true) || description.contains("louer",true)){
                categorie = "Location"
            }
            else if (description.contains("change ", true)){
                categorie = "Echange"
            }

            return Annonce(
                id,
                categorie,
                type,
                a.content.toString(),
                Wilaya.Alger, // TODO  change
                a.title.toString(),
                150, // TODO  change
                description,
                prix, // TODO  change
                a.author.toString(), // TODO  change
                imageList,
                null, // TODO  change
                a.link as String
            )
        }
    }
}


