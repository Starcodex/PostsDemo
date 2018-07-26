package com.julianotalora.posts.di.component

import com.julianotalora.posts.di.module.AllPostsModule
import com.julianotalora.posts.di.module.FavoritesPostsModule
import com.julianotalora.posts.ui.posts.all.AllPosts
import com.julianotalora.posts.ui.posts.favorites.FavoritesPosts
import dagger.Component

/**
 * Created by Bonestack on 25/07/2018.
 */
@Component(modules = arrayOf(FavoritesPostsModule::class))
interface FavoritesPostsComponent {
    fun inject(fragment: FavoritesPosts)
}