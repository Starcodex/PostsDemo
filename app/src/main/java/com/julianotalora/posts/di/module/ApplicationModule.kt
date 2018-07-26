package com.julianotalora.posts.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.julianotalora.posts.ApplicationContext
import com.julianotalora.posts.BaseApp
import com.julianotalora.posts.api.PostsApi
import com.julianotalora.posts.data.AppDatabase
import com.julianotalora.posts.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Bonestack on 24/07/2018.
 */
@Module
class ApplicationModule(private val  baseApp: BaseApp) {


    @Provides
    fun provideApplication(): Application {
        return baseApp
    }


    @Provides
    fun provideApplicationContext(): Context {
        return baseApp.applicationContext
    }

    @Provides
    fun providesAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "main-db").allowMainThreadQueries().build()

    @Provides
    fun providesPostDao(database: AppDatabase) = database.postDao()

}