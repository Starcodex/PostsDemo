package com.julianotalora.posts.di.component
import com.julianotalora.posts.data.PostDao
import com.julianotalora.posts.di.module.ApplicationModule
import com.julianotalora.posts.di.module.DetailsPostModule
import com.julianotalora.posts.ui.details.DetailsActivity
import dagger.Component

/**
 * Created by Bonestack on 25/07/2018.
 */
@Component(modules = arrayOf(DetailsPostModule::class))
interface DetailsPostComponent {
    fun inject(activity: DetailsActivity)

}