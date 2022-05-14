package com.local.shaluassignment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.local.shaluassignment.R
import com.local.shaluassignment.models.IssueListModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_issue_list.view.*
import java.text.SimpleDateFormat

class IssueListAdapter(
    private var list: List<IssueListModel>,
    var context: Context,
    val clickListener: BtnClickListener
) : RecyclerView.Adapter<IssueListAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_issue_list, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val issueItem = list[position]
        holder.itemView.tvIssueTitle.text = "Issue Title : " + issueItem.title
        holder.itemView.tvIssueDescription.text =
            "Issue Description : " + issueItem.issueDescription

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("mm-dd-yyyy")
        val output: String = formatter.format(parser.parse(issueItem.updatedAt?.toString()))
        holder.itemView.tvUpdatedDate.text = "Date : " + output

        holder.itemView.tvUserName.text = "User Name : " + issueItem.user?.userName.toString()
        Picasso.get().load(issueItem.user?.avatarUrl.toString()).into(holder.itemView.ivUserProfile)

        holder.itemView.btnIssueComments.setOnClickListener {
            clickListener.commentListEvent(
                issueItem.commentsUrl.toString(),
                issueItem.issueDescription.toString()
            )
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface BtnClickListener {
        fun commentListEvent(commentUrl: String, issueDesc: String)
    }
}
