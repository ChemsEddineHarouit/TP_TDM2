package com.example.tdm2.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Checkable
import android.widget.ImageView
import android.widget.TextView
import com.example.tdm2.R
import com.example.tdm2.controllers.WilayaController
import com.example.tdm2.enumerations.Wilaya


class WilayaAdapter( mesWilayasList: List<Wilaya>): RecyclerView.Adapter<WilayaAdapter.ViewHolder>(){

    var mesWilayasList = mesWilayasList
    val wilayaList = WilayaController.getAllWilayas()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wilaya = wilayaList[position]
        holder.nom.text = wilaya.toString()
        holder.matricule.text = wilaya.getMatricule().toString()
        if(wilaya in mesWilayasList){
            holder.check.isChecked = true
        }
        val checkBox = holder.check as CheckBox
        checkBox.setTag(wilaya.getMatricule())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_wilaya, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return wilayaList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nom = itemView.findViewById<TextView>(R.id.row_wilaya_name)
        val matricule = itemView.findViewById<TextView>(R.id.row_wilaya_matricule)
        val check = itemView.findViewById<ImageView>(R.id.row_wilaya_check) as Checkable
    }
}
