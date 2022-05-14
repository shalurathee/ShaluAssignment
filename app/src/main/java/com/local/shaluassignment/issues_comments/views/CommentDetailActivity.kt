package com.local.shaluassignment.issues_comments.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.local.shaluassignment.R
import com.local.shaluassignment.adapters.CommentDetailAdapter
import com.local.shaluassignment.issues_comments.viewmodels.IssueCommentViewModel
import com.local.shaluassignment.models.CommentListModel
import com.local.shaluassignment.utils.ApiConstantsUrl
import kotlinx.android.synthetic.main.activity_comment_detail.*
import kotlinx.android.synthetic.main.layout_header.*

class CommentDetailActivity : AppCompatActivity() {

    lateinit var issueAdapter: CommentDetailAdapter
    lateinit var commentList: List<CommentListModel>
    var comment_api_url: String = ""
    var issue_description: String = ""
    lateinit var issuesCommentViewModel: IssueCommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_detail)
        initUI()
    }

    fun initUI() {
        rvCommentList.visibility = View.GONE
        tvNoCommentFound.visibility = View.GONE
        tvHeaderlabel.text = "Comment List"

        issuesCommentViewModel =
            ViewModelProvider.NewInstanceFactory().create(IssueCommentViewModel::class.java)
        comment_api_url = intent.getStringExtra(ApiConstantsUrl.COMMENT_LIST).toString()
        issue_description = intent.getStringExtra(ApiConstantsUrl.ISSUE_DESCRIPTION).toString()

        tvIssueDescription.text = "Issue Description : "+issue_description
        getCommentIssueList(comment_api_url)

        ivBackIcon.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getCommentIssueList(apiUrl: String) {
        issuesCommentViewModel.getIssuesComment(apiUrl).observe(this, {
            if (it != null) {
                commentList = it

                if (commentList.size > 0) {
                    rvCommentList.visibility = View.VISIBLE
                    tvNoCommentFound.visibility = View.GONE
                } else {
                    rvCommentList.visibility = View.GONE
                    tvNoCommentFound.visibility = View.VISIBLE
                }
                setAdapter()

            } else {
                rvCommentList.visibility = View.GONE
                tvNoCommentFound.visibility = View.VISIBLE
                tvNoCommentFound.text = "Something wrong with Api Call..."
            }

        })
    }

    private fun setAdapter() {
        issueAdapter = CommentDetailAdapter(commentList, this)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        rvCommentList.layoutManager = linearLayoutManager
        rvCommentList.adapter = issueAdapter
        rvCommentList.isNestedScrollingEnabled = false
        issueAdapter.notifyDataSetChanged()
    }
}