package com.julianotalora.posts.di.module

import android.app.Activity
import com.julianotalora.posts.api.ApiModule
import com.julianotalora.posts.api.PostsApi
import com.julianotalora.posts.data.PostDao
import com.julianotalora.posts.ui.details.DetailsActivity
import com.julianotalora.posts.ui.details.DetailsContract
import com.julianotalora.posts.ui.details.DetailsPostInteractor
import com.julianotalora.posts.ui.details.DetailsPostPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Bonestack on 25/07/2018.
 */
@Module(includes = arrayOf(ApiModule::class ))
class DetailsPostModule(private var mActivity: DetailsActivity) {

    @Provides
    fun provideActivity(): DetailsActivity {
        return activity
    }

    private lateinit var activity: DetailsActivity

    init{ this.activity = mActivity }

    @Provides
    fun provideDetailsPostInteractor(postDao:PostDao,postsApi: PostsApi): DetailsPostInteractor {
        return DetailsPostInteractor(postDao,postsApi)
    }

    @Provides
    fun provideDetailsPostPresenter(interactor:DetailsPostInteractor): DetailsContract.Presenter {
        return DetailsPostPresenter(activity,interactor)
    }


}