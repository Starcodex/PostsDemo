package com.julianotalora.posts.ui.posts.favorites

import com.julianotalora.posts.models.Post

/**
 * Created by Bonestack on 25/07/2018.
 */
class FavoritesPostsPresenter(fragment: FavoritesPosts, interactor: FavoritesPostsInteractor) :FavoritesContract.Presenter{

    lateinit var view: FavoritesContract.View
    lateinit var interactor: FavoritesPostsInteractor

    init {
        this.view = fragment
        this.interactor = interactor
        this.interactor.setPresenter(this)
    }

    override fun getFavoritesList() {
        interactor.getFavoritesList()
    }

    override fun setfavoritesList(body: List<Post>){
        view.setList(body)
    }

}