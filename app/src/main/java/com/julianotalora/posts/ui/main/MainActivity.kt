package com.julianotalora.posts.ui.main

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.julianotalora.posts.R
import com.julianotalora.posts.di.component.DaggerActivityComponent
import com.julianotalora.posts.di.module.ActivityModule
import com.julianotalora.posts.ui.posts.adapters.PostsViewPagerAdapter
import com.julianotalora.posts.ui.posts.all.AllPosts
import com.julianotalora.posts.ui.posts.favorites.FavoritesPosts
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import com.julianotalora.posts.models.Post


class MainActivity : AppCompatActivity(), MainContract.View {

    override fun removeFavorite(post: Post) {
        Log.d("FAVORITE","Working")
    }


    @Inject lateinit var presenter: MainContract.Presenter
    private lateinit var postViewPagerAdapter: PostsViewPagerAdapter



    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setSupportActionBar(toolbar)
        injectDependency()
        initViewPager()
    }


    private fun injectDependency() {
        DaggerActivityComponent.builder().activityModule(ActivityModule(this)).build().inject(this)
    }

    fun initViewPager() {
        postViewPagerAdapter = PostsViewPagerAdapter(getSupportFragmentManager(), this, AllPosts(), FavoritesPosts())
        pager.setAdapter(postViewPagerAdapter)
        tabs.setupWithViewPager(pager)
        tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab!!.position==1){
                    // Refresh favorites fragment
                    postViewPagerAdapter.onRefresh(supportFragmentManager)
                }
            }

        })

    }

}
