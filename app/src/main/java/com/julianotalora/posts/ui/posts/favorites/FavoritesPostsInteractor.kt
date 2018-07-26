package com.julianotalora.posts.ui.posts.favorites

import android.util.Log
import com.julianotalora.posts.data.PostDao
import com.julianotalora.posts.data.PostEntity
import com.julianotalora.posts.models.Post
import io.reactivex.Flowable

/**
 * Created by Bonestack on 25/07/2018.
 */
class FavoritesPostsInteractor(postDao: PostDao) :FavoritesContract.Interactor{

    var dao = postDao
    lateinit var presenter: FavoritesContract.Presenter

    override fun setPresenter(favoritesPostsPresenter: FavoritesPostsPresenter) {
        this.presenter = favoritesPostsPresenter
    }

    override fun getFavoritesList() {
            var list: List<Post> = dao.getAllFavorites(true)
        presenter.setfavoritesList(list)
        Log.d("LIST",""+list)
    }

}