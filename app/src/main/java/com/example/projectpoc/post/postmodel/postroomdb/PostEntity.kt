package com.example.projectpoc.post.postmodel.postroomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Post_Db")
class PostEntity {

    @PrimaryKey
    @ColumnInfo(name = "Post_Id")
    var id: Int = 0

    @ColumnInfo(name = "Post_title")
    var title: String? = null

    @ColumnInfo(name = "Post_Body")
    var body: String? = null
}