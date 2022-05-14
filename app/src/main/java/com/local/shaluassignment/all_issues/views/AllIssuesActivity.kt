package com.local.shaluassignment.all_issues.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.local.shaluassignment.R
import com.local.shaluassignment.adapters.IssueListAdapter
import com.local.shaluassignment.all_issues.viewmodels.IssuesViewModel
import com.local.shaluassignment.issues_comments.views.CommentDetailActivity
import com.local.shaluassignment.models.IssueListModel
import com.local.shaluassignment.utils.ApiConstantsUrl
import com.local.shaluassignment.utils.WebServicesCall
import kotlinx.android.synthetic.main.activity_all_issues.*
import kotlinx.android.synthetic.main.layout_header.*
import java.lang.reflect.Type


class AllIssuesActivity : AppCompatActivity() {

    lateinit var issueAdapter :IssueListAdapter
    lateinit var issueList : List<IssueListModel>

    lateinit var issuesViewModel :IssuesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_issues)
        initUI()
    }

    fun initUI(){

        rvIssueList.visibility = View.GONE
        tvNoRecordFound.visibility =View.GONE
        ivBackIcon.visibility =View.GONE

        tvHeaderlabel.text = "Issue List"

        issuesViewModel =ViewModelProvider.NewInstanceFactory().create(IssuesViewModel::class.java)
        getAllIssueList()
    }

    fun callCommentActivity(commentUrl:String){
        val userProfileIntent = Intent(this,CommentDetailActivity::class.java)
        userProfileIntent.putExtra(ApiConstantsUrl.COMMENT_LIST, commentUrl)
        startActivity(userProfileIntent)
    }

    private fun getAllIssueList() {
        issuesViewModel.getAllIssues().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                issueList = it

                if (issueList.size>0){
                    rvIssueList.visibility = View.VISIBLE
                    tvNoRecordFound.visibility =View.GONE
                }else{
                    rvIssueList.visibility = View.GONE
                    tvNoRecordFound.visibility =View.VISIBLE
                }
                setAdapter()
            } else {
                rvIssueList.visibility = View.GONE
                tvNoRecordFound.visibility =View.VISIBLE
                tvNoRecordFound.text = "Something wrong with Api Call..."
            }
        })
    }

    private fun setAdapter(){
        issueAdapter = IssueListAdapter( issueList,this, object :
            IssueListAdapter.BtnClickListener {
            override fun commentListEvent(commentUrl: String) {
                callCommentActivity(commentUrl)
            }

        })
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        rvIssueList.layoutManager = linearLayoutManager
        rvIssueList.adapter = issueAdapter
        issueAdapter.notifyDataSetChanged()
    }

}