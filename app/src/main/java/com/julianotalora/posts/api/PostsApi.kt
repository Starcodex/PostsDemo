package com.julianotalora.posts.api

import com.julianotalora.posts.api.responses.PostsResponse
import com.julianotalora.posts.data.PostEntity
import com.julianotalora.posts.models.Comment
import com.julianotalora.posts.models.Post
import com.julianotalora.posts.models.User
import com.julianotalora.posts.util.Constants
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Bonestack on 24/07/2018.
 */
interface PostsApi {

    @GET("posts")
    fun getPostsList(): Call<List<PostEntity>>

    @GET("posts/{post}/comments")
    fun getPostComments(@Path("post") post:Int): Call<List<Comment>>

    @GET("users/{user}")
    fun getUser(@Path("user") user:Int): Call<User>

}
