package com.julianotalora.posts.api.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.julianotalora.posts.di.scope.PerApplication
import com.julianotalora.posts.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Bonestack on 24/07/2018.
 */
@Module(includes = arrayOf(NetworkModule::class))
class RetrofitModule {


    @Provides
    internal fun provideGsonConverterFactory() : GsonConverterFactory {
        val GSON = GsonBuilder()
                .registerTypeAdapterFactory(AutoValueAdapterFactory())
                .create()
        return GsonConverterFactory.create(GSON)
    }

    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient,
                                 converterFactory: GsonConverterFactory) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }

/*    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,gson: Gson): Retrofit {

        return retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory(gson)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .build()

        return Retrofit.Builder()
               // .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .build()
    }*/


}