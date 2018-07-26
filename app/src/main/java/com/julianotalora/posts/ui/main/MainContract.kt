package com.julianotalora.posts.ui.main

import com.julianotalora.posts.models.Post
import com.julianotalora.posts.ui.base.BaseContract

/**
 * Created by Bonestack on 24/07/2018.
 */
class MainContract {

    interface View: BaseContract.View {

        fun removeFavorite(post:Post)
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
    }
}