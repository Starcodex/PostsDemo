package com.julianotalora.posts.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.julianotalora.posts.models.Post

/**
 * Created by Bonestack on 24/07/2018.
 */
class PostsResponse(val posts: Array<List<Post>>)
/*
{
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
 */
