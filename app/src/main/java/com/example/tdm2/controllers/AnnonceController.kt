package com.example.tdm2.controllers

import android.content.Context
import com.example.tdm2.Storage.AnnonceStorage
import com.example.tdm2.models.Annonce
import com.example.tdm2.enumerations.Wilaya
import java.util.*


class AnnonceController private constructor(){

    //This is the annonceList to fill with the API from Booking
    var mesAnnonceList:List<Annonce>? = null
    val annonceAllMap = TreeMap<Int, Annonce>()


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
                listOf("https://d1ez3020z2uu9b.cloudfront.net/imagecache/rental-homes-photos-spain/Original/23591/9582456-23591-Marbella-Villa_Crop_725_600.jpg",
                    "https://odis.homeaway.com/odis/listing/7e04139f-1678-4a69-a9dc-d86be6bd80c6.c10.jpg",
                    "https://d1ez3020z2uu9b.cloudfront.net/imagecache/rental-homes-photos-spain/Original/7331/1653978-7331-Adeje-Villa_Crop_725_600.jpg",
                    "https://cdn.samui-villa.com/cache/512-samui-en/villas/skydream-villa/skydream-villa-dji-0835-edit-589a89aea569e.jpg"),
                listOf("https://developers.google.com/training/images/tacoma_narrows.mp4")))

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

        updateAllAnnonceMap()
    }

    private fun updateAllAnnonceMap(){
        for (annonce in annonceList){
            annonceAllMap.put(annonce.id, annonce)
        }
    }

    fun getMesAnnoncesList(context: Context): List<Annonce>{
        if(mesAnnonceList == null){
            return loadMesAnnonces(context)
        }
        else{
            return mesAnnonceList as List<Annonce>
        }
    }

    fun getAnnonceById(id: Int): Annonce{
        return annonceAllMap.getValue(id)
    }

    fun loadMesAnnonces(context: Context): List<Annonce> {
        val mesAnnoncesListLoaded = AnnonceStorage.load(context)
        //TODO Sort Annonces and define CompareTo in Annonce with date
        this.mesAnnonceList = mesAnnoncesListLoaded
        return mesAnnoncesListLoaded
    }

    fun saveMesAnnonces(context: Context) {
        val mesAnnoncesListToSave = this.mesAnnonceList
        if(mesAnnoncesListToSave != null){
            AnnonceStorage.save(context, mesAnnoncesListToSave)
        }
    }

    fun addAnnonceToMesAnnonces(annonce: Annonce){
        try {
            val mesAnnonceListTemp = this.mesAnnonceList?.toMutableList()
            if(mesAnnonceListTemp != null &&  !(annonce in mesAnnonceListTemp)){
                mesAnnonceListTemp.add(annonce)
                this.mesAnnonceList = mesAnnonceListTemp
            }
        }
        catch (e: Exception){
            print("----------------------------" + e)
        }

    }

    fun removeAnnoncefromMesAnnonces(annonce: Annonce){
        try {
            val mesAnnonceListTemp = this.mesAnnonceList?.toMutableList()
            if(mesAnnonceListTemp != null &&  (annonce in mesAnnonceListTemp)){
                mesAnnonceListTemp.remove(annonce)
                this.mesAnnonceList = mesAnnonceListTemp
            }
        }
        catch (e: Exception){
            print("----------------------------" + e)
        }

    }


    private object Holder{
        val INSTANCE= AnnonceController()

    }
    companion object {
        val instance: AnnonceController by lazy { Holder.INSTANCE }
    }

}


