package com.julianotalora.posts.di.module

import android.app.Activity
import com.julianotalora.posts.ui.main.MainActivity
import com.julianotalora.posts.ui.main.MainContract
import com.julianotalora.posts.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Bonestack on 24/07/2018.
 */
@Module
class ActivityModule(private var activity: MainActivity) {

    @Provides
    fun provideActivity(): MainContract.View {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}