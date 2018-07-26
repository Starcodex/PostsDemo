package com.julianotalora.posts.ui.posts.favorites

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.julianotalora.posts.BaseApp
import com.julianotalora.posts.R
import com.julianotalora.posts.di.component.DaggerDetailsPostComponent
import com.julianotalora.posts.di.component.DaggerFavoritesPostsComponent
import com.julianotalora.posts.di.module.*
import com.julianotalora.posts.models.Post
import com.julianotalora.posts.ui.details.DetailsActivity
import com.julianotalora.posts.ui.main.MainActivity
import com.julianotalora.posts.ui.main.MainContract
import com.julianotalora.posts.ui.posts.adapters.PostsAdapter
import com.julianotalora.posts.ui.posts.all.AllPosts
import com.julianotalora.posts.ui.posts.all.AllPostsContract
import kotlinx.android.synthetic.main.all_posts.*
import kotlinx.android.synthetic.main.all_posts.view.*
import javax.inject.Inject

/**
 * Created by Bonestack on 24/07/2018.
 */
class FavoritesPosts : Fragment(), FavoritesContract.View,PostsAdapter.OnItemClickListener {


    val SELECT_FAVORITE = 325  // The request code

    override fun onClickItem(position: Int) {

        Log.d("READED",""+postAdapter.getItem(position).readed)

        var intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra("post",postAdapter.getItem(position))
        startActivityForResult(intent, SELECT_FAVORITE);

        postAdapter.getItem(position).readed = true
        postAdapter.notifyItemChanged(position)
    }

    // Presenter
    @Inject lateinit var presenter: FavoritesContract.Presenter
    private lateinit var postAdapter: PostsAdapter
    internal lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initComponent()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.all_posts, container, false)
        view.fab.visibility = View.GONE
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        refresh()
    }

    fun initComponent() {
        DaggerFavoritesPostsComponent.builder()
                .applicationModule(ApplicationModule(activity!!.applicationContext as BaseApp))
                .activityModule(ActivityModule((activity as MainActivity?)!!))
                .allPostsModule(AllPostsModule(AllPosts()))
                .favoritesPostsModule(FavoritesPostsModule(this)).build().inject(this)
    }


    fun initRecyclerView() {
        mLayoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL, false)
        recyclerView.setLayoutManager(mLayoutManager)
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayout.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
        val itemList = java.util.ArrayList<Post>()
        postAdapter = PostsAdapter(itemList, this)
        postAdapter.setOnItemClickListener(this)
        recyclerView.setAdapter(postAdapter);
    }

    private fun refresh() {
        presenter.getFavoritesList()
    }

    override fun setList(body: List<Post>?) {
        postAdapter.setList(body!!)
        postAdapter.notifyDataSetChanged()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            var retrievedPost = data!!.getParcelableExtra<Post>("post")
            var pos = postAdapter.getItemPosition(retrievedPost.id)
            if (!retrievedPost.favorite){

                var size = postAdapter.getFullList().size
                postAdapter.getFullList().removeAt(pos)
                postAdapter.notifyItemRemoved(pos)
                postAdapter.notifyItemRangeChanged(pos, size);

            }

            var allPosts: AllPostsContract.View = AllPosts()
            allPosts.removeFavorite(retrievedPost)

        }
    }
}


