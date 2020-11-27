package com.example.projectpoc.post.postmodel.postroomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {

    @Query("SELECT * FROM Post_Db")
    fun fetchAllPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(posts: List<PostEntity>)

    @Query("DELETE FROM Post_Db")
    fun deleteAll()
}