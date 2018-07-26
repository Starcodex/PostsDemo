package com.julianotalora.posts.ui.details

import com.julianotalora.posts.data.PostDao
import com.julianotalora.posts.models.Comment
import com.julianotalora.posts.models.Post
import com.julianotalora.posts.models.User
import com.julianotalora.posts.ui.main.MainContract
import javax.inject.Inject

/**
 * Created by Bonestack on 25/07/2018.
 */
class DetailsPostPresenter (mView: DetailsContract.View, mInteractor: DetailsContract.Interactor): DetailsContract.Presenter  {

    var view: DetailsContract.View
    var interactor: DetailsContract.Interactor

    init {
        this.view = mView
        this.interactor = mInteractor
        this.interactor.setPresenter(this)

    }

    override fun favoritePressed(post: Post) {
        post.favorite = !post.favorite

        if(post.favorite)
            interactor.addNewPost(post)
        else
            interactor.removePost(post)
    }

    override fun setUserData(body: User?) {
        view.setUserData(body)
    }

    override fun setListComments(body: List<Comment>?) {
        view.setListComments(body)
    }

    override fun loadData(id: Int, userId: Int) {
        interactor.loadUserDetails(userId)
        interactor.loadComments(id)
    }



}