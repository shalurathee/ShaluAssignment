package com.local.shaluassignment.utils

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


object WebServicesCall {
    const val CONTENT_TYPE = "Content-Type"
    const val DEVICE_ID = "device_id"
    const val AUTHORIZATION = "Authorization"
    const val APPLICATION_JSON = "application/json"

    interface ResponseListener {
        fun onSuccess(response: String)
        fun onError(errorMessage: String)
    }

    fun getRequest(url: String, isAuthenticationRequired: Boolean = true, headerMap: HashMap<String, String>? = null, listener: ResponseListener, queryParameter: HashMap<String, String>? = null) {
        val builder = AndroidNetworking.get(url)
        if (isAuthenticationRequired) {
            val map = HashMap<String, String>()
//            map.put(AUTHORIZATION,"Bearer beadfc304e2c755d816f1861b5bf082bff72868c")
//            map.put(CONTENT_TYPE, APPLICATION_JSON)
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
//
//                try {
//                    val `object` = JSONObject(response)
//                    val array = `object`.getJSONArray("users")
//                    for (i in 0 until array.length()) {
//                        val object1 = array.getJSONObject(i)
//                        val name = object1.getString("name")
//                        val email = object1.getString("email")
//                        list.add(UserInfo(name, email))
//                    }
//                    textView.setText(
//                        """email: ${list.get(0).email.toString()}
//name: ${list.get(0).name}"""
//                    )
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
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