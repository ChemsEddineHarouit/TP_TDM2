package com.example.tdm2.controllers

import android.content.Context
import android.util.Log
import com.example.tdm2.R
import com.example.tdm2.Storage.AnnonceStorage
import com.example.tdm2.adapters.AnnonceAdapter
import com.example.tdm2.models.Annonce
import com.example.tdm2.enumerations.Wilaya
import com.prof.rssparser.Parser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*


class AnnonceController private constructor(){

    //This is the annonceList to fill with the API from Booking
    var mesAnnonceList:List<Annonce>? = null
    val annonceAllMap = TreeMap<Int, Annonce>()


    var annonceList = mutableListOf<Annonce>()

    init {

    }

    fun updateAllAnnonceMap(){
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


