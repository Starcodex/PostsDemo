package com.julianotalora.posts.models

/**
 * Created by Bonestack on 24/07/2018.
 */
data class Comment(val id: Int, val postId: Int, val name: String, val email: String, val body: String)