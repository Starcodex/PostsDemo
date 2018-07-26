package com.julianotalora.posts.ui.posts.all

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.LinearLayoutManager
import com.julianotalora.posts.R
import com.julianotalora.posts.di.component.DaggerAllPostsComponent
import com.julianotalora.posts.di.module.AllPostsModule
import com.julianotalora.posts.models.Post
import com.julianotalora.posts.ui.posts.adapters.PostsAdapter
import kotlinx.android.synthetic.main.all_posts.*
import javax.inject.Inject
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.*
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import com.julianotalora.posts.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.all_posts.view.*
import android.view.MenuInflater
import com.julianotalora.posts.BaseApp
import com.julianotalora.posts.di.module.ApplicationModule
import com.julianotalora.posts.ui.main.MainActivity
import com.julianotalora.posts.util.SwipeToDelete


/**
 * Created by Bonestack on 24/07/2018.
 */
class AllPosts : Fragment(), AllPostsContract.View, PostsAdapter.OnItemClickListener {


    val SELECT_FAVORITE = 325  // The request code
    // Presenter
    @Inject lateinit var presenter: AllPostsContract.Presenter
    companion object { private lateinit var postAdapter: PostsAdapter }
    internal lateinit var mLayoutManager: LinearLayoutManager




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initComponent()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.all_posts, container, false)
        view.fab.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                postAdapter.deleteAllPosts()
                presenter.deleteAllFavorites()
            }
        })
        setHasOptionsMenu(true);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        refresh()
    }

    fun initComponent() {
        // Inject dependencies
        DaggerAllPostsComponent.builder().applicationModule(ApplicationModule(activity!!.applicationContext as BaseApp)).allPostsModule(AllPostsModule(this)).build().inject(this)
    }

    fun initRecyclerView() {
        mLayoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL, false)
        recyclerView.setLayoutManager(mLayoutManager)
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
        val itemList = java.util.ArrayList<Post>()
        postAdapter = PostsAdapter(itemList, this)
        postAdapter.setOnItemClickListener(this)
        recyclerView.setAdapter(postAdapter);

        val swipeHandler = object : SwipeToDelete(activity!! as MainActivity) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var size = postAdapter.getFullList().size
                presenter.deleteSinglePost(postAdapter.getItem(viewHolder.adapterPosition))
                postAdapter.getFullList().removeAt(viewHolder.adapterPosition)
                postAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                postAdapter.notifyItemRangeChanged(viewHolder.adapterPosition, size)

            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun removeFavorite(retrievedPost: Post?) {
        Log.d("ALLPOSTS","IT WORKSSSS "+retrievedPost)
        var pos = postAdapter.getItemPosition(retrievedPost!!.id)
        postAdapter.getItem(pos).favorite = false
        postAdapter.notifyDataSetChanged()
    }

    private fun refresh() {
        postAdapter.deleteAllPosts()
        presenter.loadPosts()
    }

    override fun setList(body: List<Post>?) {
        postAdapter.setList(body!!)
        postAdapter.notifyDataSetChanged()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK){
            var retrievedPost = data!!.getParcelableExtra<Post>("post")
            var pos = postAdapter.getItemPosition(retrievedPost.id)
            postAdapter.getItem(pos).favorite = retrievedPost.favorite
            postAdapter.notifyItemChanged(pos)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.menu_refresh -> {
                refresh()
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onClickItem(position: Int) {
        postAdapter.getItem(position).readed = true
        var intent = Intent(activity,DetailsActivity::class.java)
        intent.putExtra("post",postAdapter.getItem(position))
        startActivityForResult(intent, SELECT_FAVORITE);
        postAdapter.notifyItemChanged(position)
    }

}
