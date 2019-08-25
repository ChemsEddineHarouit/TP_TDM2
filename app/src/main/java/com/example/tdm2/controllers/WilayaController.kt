package com.example.tdm2.controllers

import android.content.Context
import com.example.tdm2.Storage.WilayaStorage
import com.example.tdm2.enumerations.Wilaya
import java.util.*

class WilayaController private constructor(){

    var mesWilayaList : List<Wilaya>? = null

    fun getMesWilayas(context: Context): List<Wilaya> {
        if(mesWilayaList == null){
            return loadMesWilayas(context)
        }
        else{
            return mesWilayaList as List<Wilaya>
        }
    }

    fun getMesWilayasMatricule(context: Context): List<Int> {
        val mesWilayaList = this.getMesWilayas(context)
        val mesWilayaListMatricule = arrayListOf<Int>()
        for (wil in mesWilayaList){
            mesWilayaListMatricule.add(wil.getMatricule())
        }
        return mesWilayaListMatricule
    }

    fun addWilayaToMesWilayas(matricule: Int){
        try {
            val wilaya = Wilaya.getFromMatricule(matricule) as Wilaya
            val mesWilayasListTemp = this.mesWilayaList?.toMutableList()
            if(mesWilayasListTemp != null &&  !(wilaya in mesWilayasListTemp)){
                mesWilayasListTemp.add(wilaya)
                this.mesWilayaList = mesWilayasListTemp
            }
        }
        catch (e: Exception){
            print("----------------------------" + e)
        }

    }

    fun removeWilayafromMesWilayas(matricule: Int){
        try {
            val wilaya = Wilaya.getFromMatricule(matricule) as Wilaya
            val mesWilayasListTemp = this.mesWilayaList?.toMutableList()
            if(mesWilayasListTemp != null &&  wilaya in mesWilayasListTemp){
                mesWilayasListTemp.remove(wilaya)
                this.mesWilayaList = mesWilayasListTemp
            }
        }
        catch (e: Exception){
            print("----------------------------" + e)
        }

    }

    fun loadMesWilayas(context: Context): List<Wilaya> {
        val mesWilayaListLoaded = WilayaStorage.load(context)
        mesWilayaListLoaded.sortedWith(compareBy { it.getMatricule() })
        this.mesWilayaList = mesWilayaListLoaded
        return mesWilayaListLoaded
    }

    fun saveMesWilayas(context: Context) {
        val mesWilayaListToSave = this.mesWilayaList
        if(mesWilayaListToSave != null){
            WilayaStorage.save(context, mesWilayaListToSave)
        }
    }

    private object Holder{
        val INSTANCE= WilayaController()

    }
    companion object {
        val instance: WilayaController by lazy { Holder.INSTANCE }

        fun getAllWilayas() : List<Wilaya>{
            val allWilayasList = Wilaya.values()
            return allWilayasList.sortedWith(compareBy { it.getMatricule() })
        }
    }

}