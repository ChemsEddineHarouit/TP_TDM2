package com.example.tdm2.models

import com.example.tdm2.enumerations.Wilaya

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
}