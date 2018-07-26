package com.julianotalora.posts.ui.posts.favorites

import com.julianotalora.posts.models.Post

/**
 * Created by Bonestack on 25/07/2018.
 */
class FavoritesContract{
    interface View{

        fun setList(body: List<Post>?)
    }

    interface Presenter{
        fun getFavoritesList()

        fun setfavoritesList(body: List<Post>)
    }

    interface Interactor{
        fun setPresenter(favoritesPostsPresenter: FavoritesPostsPresenter)
        fun getFavoritesList()

    }
}