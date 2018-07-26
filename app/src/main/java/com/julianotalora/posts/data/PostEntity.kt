package com.julianotalora.posts.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Bonestack on 25/07/2018.
 */
@Entity(tableName = "post")
data class PostEntity(@ColumnInfo(name = "id")
                      @PrimaryKey()
                      var id: Long = 0,@ColumnInfo(name = "userId") var userId: Int,
                      @ColumnInfo(name = "title") var title: String,
                      @ColumnInfo(name = "body") var body: String,
                      @ColumnInfo(name = "readed") var readed: Boolean,
                      @ColumnInfo(name = "favorite") var favorite: Boolean) {

}