package com.example.tdm2.Storage

import android.content.Context
import com.example.tdm2.controllers.AnnonceController
import com.example.tdm2.models.Annonce
import com.google.gson.Gson
import java.io.File
import java.io.FileWriter

class AnnonceStorage {

    companion object {
        val annoncesFileName = "annonces.json"
        fun save(context: Context, annoncesListToSave : List<Annonce> ) {
            try {
                val fileName = context.getFilesDir().getPath().toString() + annoncesFileName
                val gson = Gson()
                val jsonAnonncesListToSave = gson.toJson(annoncesListToSave)
                val annoncesFile = FileWriter(fileName, false)
                annoncesFile.flush()
                annoncesFile.write(jsonAnonncesListToSave)
                annoncesFile.close()
                println("----------------------------------saving----annonces----------------------------------------------------------")
                println(jsonAnonncesListToSave)
            }
            catch (e: Exception){
                println(e)
            }
        }
        //we need context for the file Path
        fun load(context: Context) :  List<Annonce> {
            try {
                val fileName = context.getFilesDir().getPath().toString() + annoncesFileName
                val gson = Gson()

                val jsonannoncesListLoaded = File(fileName).readText()
                val annoncesListLoaded = gson.fromJson(jsonannoncesListLoaded, Array<Annonce>::class.java).toMutableList()
                println("----------------------------------loading----annonces----------------------------------------------------------")
                println(annoncesListLoaded)
                return annoncesListLoaded as ArrayList<Annonce>
            }
            catch (e : Exception ){
                println(e)
                return listOf<Annonce>()
            }
        }
    }
}