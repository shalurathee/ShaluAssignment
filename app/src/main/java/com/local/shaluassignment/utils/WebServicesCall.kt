package com.local.shaluassignment.utils

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener

object WebServicesCall {

    interface ResponseListener {
        fun onSuccess(response: String)
        fun onError(errorMessage: String)
    }

    fun getRequest(url: String, isAuthenticationRequired: Boolean = true, headerMap: HashMap<String, String>? = null, listener: ResponseListener, queryParameter: HashMap<String, String>? = null) {
        val builder = AndroidNetworking.get(url)
        if (isAuthenticationRequired) {
            val map = HashMap<String, String>()
            builder.addHeaders(map)
        }
        Log.e("url=", url)
        if (headerMap != null)
            builder.addHeaders(headerMap)

        if (queryParameter != null) {
            builder.addQueryParameter(queryParameter)
        }
        Log.e("body=", queryParameter.toString())

        builder.build().getAsString(object : StringRequestListener {
            override fun onResponse(response: String) {
                listener.onSuccess(response)
                Log.e("response=", response)
            }

            override fun onError(anError: ANError?) {
                listener.onError("Error")
                Log.e("error=", anError?.message.toString())
            }
        })
    }
}