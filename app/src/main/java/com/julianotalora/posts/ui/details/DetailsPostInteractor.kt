package com.julianotalora.posts.ui.details

import android.util.Log
import com.julianotalora.posts.api.PostsApi
import com.julianotalora.posts.data.PostDao
import com.julianotalora.posts.data.PostEntity
import com.julianotalora.posts.models.Comment
import com.julianotalora.posts.models.Post
import com.julianotalora.posts.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Bonestack on 25/07/2018.
 */
class DetailsPostInteractor(postDao: PostDao, mApi: PostsApi) : DetailsContract.Interactor {

    lateinit var presenter: DetailsContract.Presenter
    override fun setPresenter(presenter: DetailsPostPresenter){
        this.presenter = presenter
    }

    private var api = mApi
    private var dao = postDao
    var posts = ArrayList<PostEntity>()


    override fun loadUserDetails(id:Int) {
        api.getUser(id).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                presenter.setUserData(response!!.body())
            }
            override fun onFailure(call: Call<User>?, t: Throwable?) {
            }
        })
    }

    override fun loadComments(id: Int) {
        api.getPostComments(id).enqueue(object : Callback<List<Comment>>{
            override fun onFailure(call: Call<List<Comment>>?, t: Throwable?) {

            }
            override fun onResponse(call: Call<List<Comment>>?, response: Response<List<Comment>>?) {
                presenter.setListComments(response!!.body())
            }

        })
    }


    // ----------- PERSISTANCE ------------
    // Insert Into Table
    override fun addNewPost(post: Post) {
        val newPost = PostEntity(id= post.id.toLong(),userId = post.userId,title = post.title,body= post.body,readed = post.readed,favorite = post.favorite)
        dao.insertPost(newPost)
    }

    // remove Post
    override fun removePost(post: Post){
        dao.deletePost(dao.findPostById(post.id.toLong()))
    }


}