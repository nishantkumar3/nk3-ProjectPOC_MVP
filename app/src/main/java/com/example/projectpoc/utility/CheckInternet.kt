package com.example.projectpoc.utility

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class CheckInternet(var cotext: Context) {

    var connectivity : ConnectivityManager?=null
    var info : NetworkInfo ? = null

    fun isConnected():Boolean{
        connectivity = cotext.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(connectivity != null){
            info = connectivity!!.activeNetworkInfo

            if(info != null){
                if(info!!.state == NetworkInfo.State.CONNECTED){
                    return true
                }
            }
        }
        return false
    }
}