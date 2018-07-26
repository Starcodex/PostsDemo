package com.julianotalora.posts.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Bonestack on 24/07/2018.
 */
data class Post(val id: Int, val userId: Int, val title: String, val body: String, var readed: Boolean, var favorite: Boolean) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt(userId)
        writeString(title)
        writeString(body)
        writeInt((if (readed) 1 else 0))
        writeInt((if (favorite) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Post> = object : Parcelable.Creator<Post> {
            override fun createFromParcel(source: Parcel): Post = Post(source)
            override fun newArray(size: Int): Array<Post?> = arrayOfNulls(size)
        }
    }
}