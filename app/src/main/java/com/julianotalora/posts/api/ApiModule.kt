package com.julianotalora.posts.api

import com.julianotalora.posts.api.network.RetrofitModule
import com.julianotalora.posts.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Bonestack on 24/07/2018.
 */
@Module(includes = arrayOf(RetrofitModule::class))
class ApiModule {

    private val TAG = ApiModule::class.java.simpleName

    @Provides
    @PerApplication
    fun providePostsApi(retrofit: Retrofit): PostsApi {
        return retrofit.create<PostsApi>(PostsApi::class.java)
    }

}
