package com.julianotalora.posts.ui.main

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Bonestack on 24/07/2018.
 */
class MainPresenter: MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun attach(view: MainContract.View) {
        this.view = view
    }

}