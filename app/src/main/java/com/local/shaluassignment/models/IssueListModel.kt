package com.local.shaluassignment.models

import com.google.gson.annotations.SerializedName

data class IssueListModel (
    @SerializedName("url")
    var url :String?= null,
    @SerializedName("title")
    var title :String?= null,
    @SerializedName("user")
    var user :User?= null,
    @SerializedName("updated_at")
    var updatedAt :String?= null,
    @SerializedName("body")
    var issueDescription :String?= null,

    @SerializedName("comments_url")
    var commentsUrl :String?= null

    )

data class User (
    @SerializedName("avatar_url")
    var avatarUrl :String?= null,

    @SerializedName("login")
    var userName :String?= null
)
