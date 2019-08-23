package com.example.tdm2.controllers

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Telephony

class SMSController{

    companion object{
        fun sendSMS(destination: String, message: String, context: Context){
            val message = "Salut, je partage avec vous cette annonce\nSuivez ce lien: " + message
            val uri = Uri.parse("smsto:+$destination")
            val intent = Intent(Intent.ACTION_SENDTO, uri)

            with(intent) {
                putExtra("address", "+$destination")
                putExtra("sms_body", message)
            }

            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                    //Getting the default sms app.
                    val defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(context)

                    // Can be null in case that there is no default, then the user would be able to choose
                    // any app that support this intent.
                    if (defaultSmsPackageName != null) intent.setPackage(defaultSmsPackageName)

                    context.startActivity(intent)
                }
                else -> context.startActivity(intent)
            }
        }
    }
}