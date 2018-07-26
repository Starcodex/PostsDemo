package com.julianotalora.posts.ui.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.julianotalora.posts.BaseApp
import com.julianotalora.posts.R
import com.julianotalora.posts.di.component.DaggerActivityComponent
import com.julianotalora.posts.di.component.DaggerApplicationComponent
import com.julianotalora.posts.di.component.DaggerDetailsPostComponent
import com.julianotalora.posts.di.module.ApplicationModule
import com.julianotalora.posts.di.module.DetailsPostModule
import com.julianotalora.posts.models.Comment
import com.julianotalora.posts.models.Post
import com.julianotalora.posts.models.User
import com.julianotalora.posts.ui.details.adapters.CommentsAdapter
import com.julianotalora.posts.ui.main.MainContract
import com.julianotalora.posts.ui.posts.adapters.PostsAdapter
import kotlinx.android.synthetic.main.all_posts.*
import kotlinx.android.synthetic.main.post_detail.*
import javax.inject.Inject

/**
 * Created by Bonestack on 25/07/2018.
 */
class DetailsActivity : AppCompatActivity(), DetailsContract.View {

    lateinit var post: Post

    @Inject lateinit var presenter: DetailsContract.Presenter
    internal lateinit var mLayoutManager: LinearLayoutManager
    lateinit var commentsAdapter: CommentsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_detail)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);

        injectDependency()
        initRecyclerView()
        post = intent.getParcelableExtra("post")
        loadData(post.id, post.userId, post.body)

    }

    private fun injectDependency() {
        DaggerDetailsPostComponent.builder()
                .applicationModule(ApplicationModule(applicationContext as BaseApp))
                .detailsPostModule(DetailsPostModule(this))
                .build().inject(this)

    }

    fun initRecyclerView() {
        mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        recycler_comments.setLayoutManager(mLayoutManager)
        val dividerItemDecoration = DividerItemDecoration(recycler_comments.context,LinearLayout.VERTICAL)
        recycler_comments.addItemDecoration(dividerItemDecoration)
        val itemList = java.util.ArrayList<Comment>()
        commentsAdapter = CommentsAdapter(itemList, this)
        recycler_comments.setAdapter(commentsAdapter)
    }

    override fun setUserData(body: User?) {
        user_name.text = body!!.name
        user_email.text = body.email
        user_phone.text = body.phone
        user_website.text = body.website
    }


    fun loadData(id: Int, userId: Int, body: String) {
        presenter.loadData(id, userId)
        post_body.text = body
    }

    override fun setListComments(body: List<Comment>?) {
        commentsAdapter.setList(body!!)
        commentsAdapter.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.details_activity, menu)
        this.menu = menu;
        if (post.favorite)
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_24px))
        else
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_border_24px))
        return super.onCreateOptionsMenu(menu)
    }

    fun favoritePressed() {
        if (post.favorite)
            menu!!.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_border_24px))
        else
            menu!!.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_24px))

        presenter.favoritePressed(post)

    }

    private var menu: Menu? = null
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == R.id.menu_favorite) {
            favoritePressed()
        }

        if (id == android.R.id.home) {
            var intent = Intent()
            intent.putExtra("post", post)
            setResult(Activity.RESULT_OK, intent);
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}