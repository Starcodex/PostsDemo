package com.julianotalora.posts.di.component

import android.content.Context
import android.support.v4.app.ActivityCompat
import com.julianotalora.posts.BaseApp
import com.julianotalora.posts.api.ApiModule
import com.julianotalora.posts.api.PostsApi
import com.julianotalora.posts.di.module.ApplicationModule
import com.julianotalora.posts.di.scope.PerApplication
import dagger.Component
import dagger.Provides

/**
 * Created by Bonestack on 24/07/2018.
 */
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}