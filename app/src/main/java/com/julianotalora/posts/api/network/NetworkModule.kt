package com.julianotalora.posts.api.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.julianotalora.posts.ApplicationContext
import com.julianotalora.posts.api.PostsApi
import com.julianotalora.posts.di.module.ApplicationModule
import com.julianotalora.posts.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Bonestack on 24/07/2018.
 */
@Module(includes = arrayOf(ApplicationModule::class))
class NetworkModule {

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
    }


    @Provides
    fun providePostsApi(retrofit: Retrofit) : PostsApi {
        return retrofit.create<PostsApi>(PostsApi::class.java)
    }
}