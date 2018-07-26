package com.julianotalora.posts.ui.posts.all

import android.util.Log
import com.julianotalora.posts.api.PostsApi
import com.julianotalora.posts.data.PostDao
import com.julianotalora.posts.data.PostEntity
import com.julianotalora.posts.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Bonestack on 24/07/2018.
 */
class AllPostsInteractor(postDao: PostDao, postsApi: PostsApi) : AllPostsContract.Interactor{


     override fun deleteSinglePost(postId: Int) {
        dao.deletePost(dao.findPostById(postId.toLong()))
    }


    private var api = postsApi
    var dao = postDao

    override fun deleteAllFavorites() {
        dao.deleteAllPosts()
    }


    lateinit var presenter: AllPostsContract.Presenter
    override fun setPresenter(presenter: AllPostsPresenter){
        this.presenter = presenter
    }


    override fun loadPosts() {
        if(dao.getAllPosts().size!=0){
            presenter.setList(dao.getAllPosts())
        }else {
            api.getPostsList().enqueue(object : Callback<List<PostEntity>> {
                override fun onFailure(call: Call<List<PostEntity>>?, t: Throwable?) {
                    Log.e("ERROR", "ERROR")
                }

                override fun onResponse(call: Call<List<PostEntity>>?, response: Response<List<PostEntity>>?) {
                    //presenter.setList(response!!.body(),dao.getAllPosts())
                    for (Post in response!!.body()!!) {
                        if (Post.id > 20) Post.readed = true
                    }
                    //var listEntity = response.body() as List<PostEntity>
                    dao.insertAllPosts(response.body()!!)
                    presenter.setList(dao.getAllPosts())
                }

            })
        }
    }

}