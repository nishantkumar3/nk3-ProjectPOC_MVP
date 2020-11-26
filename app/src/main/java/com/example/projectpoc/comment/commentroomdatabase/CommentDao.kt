package com.example.projectpoc.comment.commentroomdatabase

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface CommentDao {

    @Insert
    fun saveComment(comment : CommentEntity)
}