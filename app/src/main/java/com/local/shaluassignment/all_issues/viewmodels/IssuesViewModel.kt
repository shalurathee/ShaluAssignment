package com.local.shaluassignment.all_issues.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.local.shaluassignment.all_issues.repositories.IssuesRepository
import com.local.shaluassignment.models.IssueListModel

class IssuesViewModel :ViewModel() {

    fun getAllIssues(): LiveData<List<IssueListModel>> {  // act as messanger between these two
        return IssuesRepository().getAllIssuesRepository()
    }

}