package com.julianotalora.posts.ui.base

/**
 * Created by Bonestack on 24/07/2018.
 */
class BaseContract {

    interface Presenter<in T> {
        fun attach(view: T)
    }

    interface View {

    }
}