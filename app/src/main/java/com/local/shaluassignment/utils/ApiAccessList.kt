package com.local.shaluassignment.utils

import com.local.shaluassignment.models.IssueListModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiAccessList {

    @GET("okhttp/issues")
    suspend fun getQuotes() : Response<IssueListModel>
}
