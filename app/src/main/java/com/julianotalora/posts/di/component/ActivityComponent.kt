package com.julianotalora.posts.di.component

import com.julianotalora.posts.di.module.ActivityModule
import com.julianotalora.posts.ui.main.MainActivity
import dagger.Component

/**
 * Created by Bonestack on 24/07/2018.
 */
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}