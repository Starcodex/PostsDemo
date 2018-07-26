package com.julianotalora.posts.ui.posts.all

import android.util.Log
import com.julianotalora.posts.models.Post
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Bonestack on 24/07/2018.
 */
class AllPostsPresenter(mView:AllPostsContract.View,mInteractor:AllPostsContract.Interactor): AllPostsContract.Presenter {

    override fun deleteAllFavorites() {
        interactor.deleteAllFavorites()
    }

    private var view: AllPostsContract.View
    private var interactor: AllPostsContract.Interactor

    init{
        this.view = mView
        this.interactor = mInteractor
        this.interactor.setPresenter(this)
    }

    override fun loadPosts() {
        interactor.loadPosts()
    }

    override fun setList(body: List<Post>?) {
        view.setList(body)
    }

    override fun deleteSinglePost(post: Post){
        interactor.deleteSinglePost(post.id)
    }


    override fun setList(body: List<Post>?,retieved : List<Post>?) {
        Log.d("RESPONSE",""+body)
        for (Post in body!!) {
                for (p:Post in retieved!!) {
                    if(p.id==Post.id){
                        Post.favorite = p.favorite
                        Post.readed = p.readed
                    }else{
                        if (Post.id > 20) Post.readed = true
                    }
                }
        }
        view.setList(body)
    }


}