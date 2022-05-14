package com.local.shaluassignment.issues_comments.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.local.shaluassignment.all_issues.repositories.IssuesRepository
import com.local.shaluassignment.models.CommentListModel
import com.local.shaluassignment.models.IssueListModel
import com.local.shaluassignment.utils.WebServicesCall
import java.lang.reflect.Type

class IssueCommentRepository {

    fun getIssuesCommentRepository(url:String): LiveData<List<CommentListModel>> {

        val data = MutableLiveData<List<CommentListModel>>()
        WebServicesCall.getRequest(url,
            listener = object : WebServicesCall.ResponseListener {
                override fun onSuccess(response: String) {
                    var responseData: List<CommentListModel>? = null
                    val type: Type = object : TypeToken<List<CommentListModel>>() {}.type
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

    companion object{
        var issuesCommentRepository : IssueCommentRepository?= null

        val instance : IssueCommentRepository
            @Synchronized get() {
                if(issuesCommentRepository ==null)
                    issuesCommentRepository = IssueCommentRepository()

                return issuesCommentRepository!!
            }
    }
}