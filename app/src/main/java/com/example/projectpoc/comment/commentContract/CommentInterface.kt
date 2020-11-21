package com.example.projectpoc.comment.commentContract

import com.example.projectpoc.comment.commentModel.Comment

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