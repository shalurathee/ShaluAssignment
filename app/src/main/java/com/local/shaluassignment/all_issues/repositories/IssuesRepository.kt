package com.local.shaluassignment.all_issues.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.local.shaluassignment.models.IssueListModel
import com.local.shaluassignment.utils.WebServicesCall
import java.lang.reflect.Type

class IssuesRepository {

    fun getAllIssuesRepository(): MutableLiveData<List<IssueListModel>> {  // api code

        val data = MutableLiveData<List<IssueListModel>>()
        WebServicesCall.getRequest("https://api.github.com/repos/square/okhttp/issues",
            listener = object : WebServicesCall.ResponseListener {
                override fun onSuccess(response: String) {
                    var responseData: List<IssueListModel>? = null
                    val type: Type = object : TypeToken<List<IssueListModel>>() {}.type
                    try {
                        responseData = Gson().fromJson(response, type)
                        Log.e("Response", response.toString())
                    } catch (ex: Exception) {
                        Log.e("Response", ex.printStackTrace().toString())
                    }
                    data.value = responseData
                }

                override fun onError(errorMessage: String) {
                    Log.e("Error msg", errorMessage)
                }
            }
        )
        return data
    }

    /*companion object{
        var issuesRepository :IssuesRepository?= null

        val instance :IssuesRepository
        @Synchronized get() {
            if(issuesRepository ==null)
                issuesRepository =IssuesRepository()

            return issuesRepository!!
        }
    }*/
}