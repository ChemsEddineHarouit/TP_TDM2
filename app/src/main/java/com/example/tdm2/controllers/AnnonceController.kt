package com.example.tdm2.controllers

import com.example.tdm2.models.Annonce
import android.graphics.drawable.Drawable
import com.example.tdm2.enumerations.Wilaya
import java.io.InputStream
import java.net.URL


class AnnonceController private constructor(){

    //This is the annonceList to fill with the API from Booking
    val annonceList = mutableListOf<Annonce>()
    //TODO Fill AnnonceList with the API call from Booking data And delete the static filling

    init {
        //static filling
        annonceList.add(
            Annonce(1, "vente", "terrain", "localisation1", Wilaya.Alger, "titre1", 400,
                "description1", 40000, "tél 00001",
                null,
                null))

        annonceList.add(
            Annonce(2, "echange", "appartement", "localisation2", Wilaya.Alger, "titre2", 100,
                "description2", 50000, "tél 00002",
                null,
                null))

        annonceList.add(
            Annonce(3, "location", "villa", "localisation3", Wilaya.Alger, "titre3", 200,
                "description3", 700000, "tél 00003",
                listOf("https://d1ez3020z2uu9b.cloudfront.net/imagecache/rental-homes-photos-spain/Original/7331/1653978-7331-Adeje-Villa_Crop_725_600.jpg"),
                null))

        annonceList.add(
            Annonce(4, "location vacances", "Bungalow", "localisation4", Wilaya.Alger, "titre4", 90,
                "description4", 60000, "tél 00004",
                null,
                null))

        annonceList.add(
            Annonce(5, "vente", "Villa", "localisation5", Wilaya.Alger, "titre5", 120,
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


