package com.example.projectpoc.comment

import com.example.projectpoc.comment.model.Comment

interface CommentInterface {
    interface CommentVIew{
        fun handleSuccess(comments: List<Comment>)
        fun showFailureMessage(t:Throwable)
        fun showResponseCode(responseCode: Int)
    }
    interface CommentPresenter{
        fun networkCallForComment(postId: Int?)
        fun handleSuccessResponse(comments: List<Comment>)
        fun handleFailure(t: Throwable)
        fun responseNotSuccessful(responseCode : Int)
    }
    interface CommentModel{
        fun getCommentList(postId: Int?,commentPresenter: CommentPresenter)

    }
}