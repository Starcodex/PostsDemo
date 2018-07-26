package com.julianotalora.posts.data

import android.arch.persistence.room.*
import com.julianotalora.posts.models.Post
import io.reactivex.Flowable

/**
 * Created by Bonestack on 25/07/2018.
 */
@Dao
interface PostDao {

//    @Insert
//    fun insertAllPosts(list: List<Post>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(objects: List<PostEntity>)

    @Query("select * from post")
    fun getAllPosts(): List<Post>

    @Query("select * from post where id = :id")
    fun findPostById(id: Long): PostEntity

    @Query("select * from post where favorite = :favorite")
    fun getAllFavorites(favorite: Boolean): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: PostEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePost(post: PostEntity)

    @Delete
    fun deletePost(post: PostEntity)

    @Query("DELETE FROM post")
    fun deleteAllPosts()

    @Query("SELECT * FROM post LIMIT 1")
    fun getAnyPost(): PostEntity

}