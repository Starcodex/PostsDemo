package com.julianotalora.posts.di.component

import android.app.Application
import com.julianotalora.posts.api.ApiModule
import com.julianotalora.posts.di.module.AllPostsModule
import com.julianotalora.posts.di.module.ApplicationModule
import com.julianotalora.posts.ui.posts.all.AllPosts
import dagger.Component

/**
 * Created by Bonestack on 24/07/2018.
 */
@Component(modules = arrayOf(AllPostsModule::class))
interface AllPostsComponent {
    fun inject(postsFragment: AllPosts)
}