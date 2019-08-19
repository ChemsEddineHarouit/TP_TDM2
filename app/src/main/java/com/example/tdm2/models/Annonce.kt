package com.example.tdm2.models

class Annonce(id : Int, categorie : String, type : String, localisation : String,
              titre : String, surface : Int, description : String, prix : Int, contact : String,
              listPhotos : List<String>?, listVideo : List<String>?){
    val id = id
    val titre = titre
    val categorie = categorie
    val type = type
    val localisation = localisation
    val surface = surface
    val description = description
    val prix = prix
    val contact = contact
    val listPhotos = listPhotos
    val listVideo = listVideo
}