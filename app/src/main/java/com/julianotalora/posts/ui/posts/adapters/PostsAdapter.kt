package com.julianotalora.posts.ui.posts.adapters

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.julianotalora.posts.R
import com.julianotalora.posts.models.Post
import com.julianotalora.posts.ui.posts.all.AllPosts
import kotlinx.android.synthetic.main.post_item.view.*


/**
 * Created by Bonestack on 24/07/2018.
 */
class PostsAdapter(var items : ArrayList<Post>, val fragment: PostsAdapter.OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var list : ArrayList<Post>
    private lateinit var onItemClickListener: PostsAdapter.OnItemClickListener
    init {
        this.list = items
        this.onItemClickListener = fragment
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        populateItem(holder as PostViewHolder, position)
    }

    fun getItemPosition(id: Int): Int {
        for (position in 0 until list.size)
            if (list.get(position).id == id)
                return position
        return 0
    }
    // Gets the number of posts in list
    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(lists:List<Post>) {
        this.list = lists as ArrayList<Post>
    }

    fun getFullList(): ArrayList<Post> {
        return this.list
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun getItem(position: Int): Post{
        var pos = position
        if(position==1 && this.list.size== 1){
            pos = 0
        }
        return this.list.get(pos)
    }

    fun deleteAllPosts(){
        list.removeAll(list)
        notifyDataSetChanged()
    }

    fun populateItem(holder:PostViewHolder, position: Int){

        val item = list.get(position)
        holder.post_text.text = item.body
        if(item.readed){
            holder.dot_container.visibility = View.GONE
        }else{
            holder.dot_container.visibility = View.VISIBLE
        }
        if(item.favorite){
            holder.favorite_container.visibility = View.VISIBLE
        }else {
            holder.favorite_container.visibility = View.GONE
        }
        holder.posts_container.setOnClickListener { onItemClickListener.onClickItem(position)}

    }

    interface OnItemClickListener {
        fun onClickItem(position: Int)
    }

}

class PostViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val dot_container = view.dot_container
    val post_text = view.post_text
    val favorite_container = view.favorite_container
    val posts_container = view.posts_container

}