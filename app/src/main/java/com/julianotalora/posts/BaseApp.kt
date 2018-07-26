package com.julianotalora.posts

import android.app.Application
import com.julianotalora.posts.di.component.ApplicationComponent
import com.julianotalora.posts.di.component.DaggerApplicationComponent
import com.julianotalora.posts.di.module.ApplicationModule

/**
 * Created by Bonestack on 24/07/2018.
 */
class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        setup()
        if (BuildConfig.DEBUG) {

        }
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

}