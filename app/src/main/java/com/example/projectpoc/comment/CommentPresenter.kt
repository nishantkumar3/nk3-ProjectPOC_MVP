package com.example.projectpoc.comment

import com.example.projectpoc.comment.commentmodel.Comment
import com.example.projectpoc.comment.commentmodel.CommentApi

class CommentPresenter(commentVIew: CommentInterface.CommentVIew) :
    CommentInterface.CommentPresenter {

    private val view: CommentInterface.CommentVIew = commentVIew
    private val commentApi: CommentInterface.CommentModel = CommentApi()

    override fun networkCallForComment(postId: Int?) {
        commentApi.getCommentList(postId, this)
    }

    override fun handleSuccessResponse(comments: List<Comment>) {
        view.handleSuccess(comments)
    }

    override fun handleFailure(t: Throwable) {
        view.showFailureMessage(t)
    }

    override fun responseNotSuccessful(responseCode: Int) {
        view.showResponseCode(responseCode)
    }
}