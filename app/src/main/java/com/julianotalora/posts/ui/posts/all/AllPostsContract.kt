package com.julianotalora.posts.ui.posts.all

import com.julianotalora.posts.data.PostEntity
import com.julianotalora.posts.models.Post
import com.julianotalora.posts.ui.base.BaseContract

/**
 * Created by Bonestack on 24/07/2018.
 */
class AllPostsContract {

    interface View: BaseContract.View {
        fun setList(body: List<Post>?)
        fun removeFavorite(retrievedPost: Post?)
    }

    interface Presenter {
        fun loadPosts()
        fun deleteAllFavorites()
        fun setList(body: List<Post>?, retieved: List<Post>?)
        fun setList(body: List<Post>?)
        fun deleteSinglePost(post: Post)
    }

    interface Interactor{
        fun loadPosts()
        fun setPresenter(presenter: AllPostsPresenter)
        fun deleteAllFavorites()
        fun deleteSinglePost(postId: Int)
    }
}