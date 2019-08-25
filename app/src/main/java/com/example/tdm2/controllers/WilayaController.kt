package com.example.tdm2.controllers

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.tdm2.R
import com.example.tdm2.Storage.WilayaStorage
import com.example.tdm2.enumerations.Wilaya
import java.util.*
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri


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

        fun notifyIfMesWilayas(context: Context, wilaya: Wilaya, link: String) {
            val wilayaController = instance
            val mesWilayaMatricules = wilayaController.getMesWilayasMatricule(context)
            if(wilaya.getMatricule() in mesWilayaMatricules){
                val CHANNEL_ID = "com.chikeandroid.tutsplustalerts.ANDROID"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
                var builder = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle("Une Annonce convient à vos préférences")
                    .setContentText(link)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentIntent(pendingIntent)
                with(NotificationManagerCompat.from(context)) {
                    // notificationId is a unique int for each notification that you must define
                    notify(1, builder.build())
                }
            }
        }
    }

}