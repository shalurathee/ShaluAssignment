package com.local.shaluassignment.models

import com.google.gson.annotations.SerializedName

data class CommentListModel (
    @SerializedName("id")
    var id :Int?= 0,
    @SerializedName("body")
    var commentDescription :String?= null,
    @SerializedName("updated_at")
    var updatedAt :String?= null
)