package com.julianotalora.posts.di.module

import com.julianotalora.posts.api.ApiModule
import com.julianotalora.posts.api.PostsApi
import com.julianotalora.posts.data.PostDao
import com.julianotalora.posts.ui.posts.all.AllPosts
import com.julianotalora.posts.ui.posts.all.AllPostsContract
import com.julianotalora.posts.ui.posts.all.AllPostsInteractor
import com.julianotalora.posts.ui.posts.all.AllPostsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Bonestack on 24/07/2018.
 */
@Module(includes = arrayOf(ApiModule::class,ActivityModule::class))
class AllPostsModule(private var allPosts: AllPosts) {

    @Provides
    fun provideAllPosts(): AllPostsContract.View {
        return allPosts
    }

    @Provides
    fun provideAllPostsInteractor(postDao: PostDao, postsApi: PostsApi):AllPostsInteractor{
        return AllPostsInteractor(postDao,postsApi)
    }

    @Provides
    fun provideAllPostsPresenter(interactor:AllPostsInteractor): AllPostsContract.Presenter {
        return AllPostsPresenter(allPosts,interactor)
    }


}