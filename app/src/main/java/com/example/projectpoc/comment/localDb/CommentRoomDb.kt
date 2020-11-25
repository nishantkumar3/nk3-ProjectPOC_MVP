package com.example.projectpoc.comment.localDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(CommentEntity::class)],version = 1)
abstract class CommentRoomDb : RoomDatabase() {

    abstract fun commentDao(): CommentDao
}