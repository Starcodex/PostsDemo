package com.julianotalora.posts.ui.details

import com.julianotalora.posts.models.Comment
import com.julianotalora.posts.models.Post
import com.julianotalora.posts.models.User
import com.julianotalora.posts.ui.base.BaseContract
import com.julianotalora.posts.ui.main.MainContract

/**
 * Created by Bonestack on 25/07/2018.
 */
class DetailsContract {

    interface View: BaseContract.View {
        fun setUserData(body: User?)
        fun setListComments(body: List<Comment>?)
    }

    interface Presenter {
        fun loadData(id: Int,userId: Int)
        fun setUserData(body: User?)
        fun setListComments(body: List<Comment>?)
        fun favoritePressed(post: Post)
    }

    interface Interactor{

        fun setPresenter(presenter: DetailsPostPresenter)
        fun loadComments(id: Int)
        fun loadUserDetails(id: Int)
        fun addNewPost(post: Post)
        fun removePost(post: Post)
    }
}