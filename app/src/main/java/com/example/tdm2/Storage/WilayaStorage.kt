package com.example.tdm2.Storage

import android.content.Context
import com.example.tdm2.enumerations.Wilaya
import com.google.gson.Gson
import java.io.File
import java.io.FileWriter

class WilayaStorage {

    companion object {
        val wilayasFileName = "wilayas.json"
        fun save(context: Context, wilayasListToSave : List<Wilaya> ) {
            try {
                val fileName = context.getFilesDir().getPath().toString() + wilayasFileName
                val gson = Gson()
                val jsonWilayasListToSave = gson.toJson(wilayasListToSave)
                val wilayasFile = FileWriter(fileName, false)
                wilayasFile.flush()
                wilayasFile.write(jsonWilayasListToSave)
                wilayasFile.close()
                println("----------------------------------saving----Wilayas----------------------------------------------------------")
                println(jsonWilayasListToSave)
            }
            catch (e: Exception){
                println(e)
            }
        }
        //we need context for the file Path
        fun load(context: Context) :  List<Wilaya> {
            try {
                val fileName = context.getFilesDir().getPath().toString() + wilayasFileName
                val gson = Gson()

                val jsonWilayasListLoaded = File(fileName).readText()
                val wilayasListLoaded = gson.fromJson(jsonWilayasListLoaded, Array<Wilaya>::class.java).toMutableList()
                println("----------------------------------loading----Wilayas----------------------------------------------------------")
                println(wilayasListLoaded)
                return wilayasListLoaded as ArrayList<Wilaya>
            }
            catch (e : Exception ){
                println(e)
                return listOf<Wilaya>()
            }
        }
    }
}