package com.julianotalora.posts.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Bonestack on 25/07/2018.
 */
@Database(entities = arrayOf(PostEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}