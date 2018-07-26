package com.julianotalora.posts.ui.details.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.julianotalora.posts.R
import com.julianotalora.posts.models.Comment
import com.julianotalora.posts.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.comment_item.view.*

import kotlinx.android.synthetic.main.post_item.view.*

/**
 * Created by Bonestack on 25/07/2018.
 */
class CommentsAdapter(var items : ArrayList<Comment>, val activity: DetailsActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var list : ArrayList<Comment>
    private lateinit var onItemClickListener: CommentsAdapter.OnItemClickListener
    init {
        this.list = items
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //populateItem(holder,list.get(position))
        populateItem(holder as CommentViewHolder, position)
    }

    // Gets the number of posts in list
    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(lists:List<Comment>) {
        this.list = lists as ArrayList<Comment>
    }


    fun getItem(position: Int): Comment {
        return  this.list.get(position)
    }


    fun populateItem(holder:CommentViewHolder, position: Int){
        val item = list.get(position)
        holder.comment_text.text = item.body

    }

    interface OnItemClickListener {
        fun onClickItem(position: Int)
    }

}

class CommentViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val comment_text = view.comment_text

}