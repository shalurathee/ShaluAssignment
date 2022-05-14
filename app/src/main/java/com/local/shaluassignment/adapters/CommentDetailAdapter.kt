package com.local.shaluassignment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.local.shaluassignment.R
import com.local.shaluassignment.models.CommentListModel
import kotlinx.android.synthetic.main.layout_comment_list.view.*
import java.text.SimpleDateFormat

class CommentDetailAdapter (private var list: List<CommentListModel>,
                            var context: Context
) : RecyclerView.Adapter<CommentDetailAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_comment_list, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val commentItem = list[position]
        holder.itemView.tvCommentDescription.text = "Issue Description : " + commentItem.commentDescription

        //Code for changing the date format
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("mm-dd-yyyy")
        val output: String = formatter.format(parser.parse(commentItem.updatedAt?.toString()))
        holder.itemView.tvCommentUpdatedDate.text = "Date : " + output

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
