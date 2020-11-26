package com.example.projectpoc.comment.commentroomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CommentEntity {

    @PrimaryKey
    var id: Int = 0

    @ColumnInfo(name = "POST_NAME")
    var name: String = ""

    @ColumnInfo(name = "USER_EMAIL")
    var email: String = ""

    @ColumnInfo(name = "COMMENT_BODY")
    var body: String = ""
}