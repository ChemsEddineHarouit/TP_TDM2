package com.example.tdm2.controllers

import com.example.tdm2.models.Annonce

class AnnonceController private constructor(){

    //This is the annonceList to fill with the API from Booking
    var annonceList = mutableListOf<Annonce>()
    //TODO Fill AnnonceList with the API call from Booking data And delete the static filling

    init {
        //static filling
        annonceList.add(
            Annonce(1, "vente", "terrain", "localisation1", "titre1", 400,
                "description1", 40000, "tél 00001",
                null,
                null))

        annonceList.add(
            Annonce(2, "echange", "appartement", "localisation2", "titre2", 100,
                "description2", 50000, "tél 00002",
                null,
                null))

        annonceList.add(
            Annonce(3, "location", "villa", "localisation3", "titre3", 200,
                "description3", 700000, "tél 00003",
                null,
                null))

        annonceList.add(
            Annonce(4, "location vacances", "Bungalow", "localisation4", "titre4", 90,
                "description4", 60000, "tél 00004",
                null,
                null))

        annonceList.add(
            Annonce(5, "vente", "Villa", "localisation5", "titre5", 120,
                "description5", 55000, "tél 00005",
                null,
                null))
        //End of static filling
    }



    private object Holder{
        val INSTANCE= AnnonceController()

    }
    companion object {
        val instance: AnnonceController by lazy { Holder.INSTANCE }
    }

}