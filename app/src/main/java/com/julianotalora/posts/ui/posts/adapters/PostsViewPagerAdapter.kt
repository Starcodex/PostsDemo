package com.julianotalora.posts.ui.posts.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.julianotalora.posts.ui.posts.all.AllPosts
import com.julianotalora.posts.ui.posts.favorites.FavoritesPosts

/**
 * Created by Bonestack on 24/07/2018.
 */
class PostsViewPagerAdapter(fm: FragmentManager, var context: Context, internal var allPostsFragment: AllPosts, internal var favoritesPostsFragment: FavoritesPosts) : FragmentStatePagerAdapter(fm) {

    private val tabsNumber = 2

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return allPostsFragment
            1 -> return favoritesPostsFragment
            else -> return allPostsFragment
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "All"
            1 -> return "Favorites"
            else -> return "All"
        }
    }

    fun onRefresh(fm: FragmentManager) {
        val ft = fm.beginTransaction()

        ft.detach(favoritesPostsFragment).attach(favoritesPostsFragment).commit()
    }


    override fun getCount(): Int {
        return tabsNumber
    }
}