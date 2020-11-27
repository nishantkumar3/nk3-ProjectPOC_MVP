package com.example.projectpoc.post.postmodel.postroomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PostEntity::class], version = 1)
abstract class PostRoomDb : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {

        private var INSTANCE: PostRoomDb? = null

        internal fun getDatabase(context: Context): PostRoomDb {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    PostRoomDb::class.java, "Post_Db"
                )
                    .build()
            }
            return INSTANCE!!
        }
    }
}