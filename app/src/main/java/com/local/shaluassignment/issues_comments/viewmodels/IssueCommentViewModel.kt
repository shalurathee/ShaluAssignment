package com.local.shaluassignment.issues_comments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.local.shaluassignment.issues_comments.repositories.IssueCommentRepository
import com.local.shaluassignment.models.CommentListModel

class IssueCommentViewModel : ViewModel() {

    fun getIssuesComment(url:String): LiveData<List<CommentListModel>> {
        return IssueCommentRepository.instance.getIssuesCommentRepository(url)
    }

}