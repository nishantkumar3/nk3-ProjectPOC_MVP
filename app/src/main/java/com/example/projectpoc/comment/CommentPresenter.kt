package com.example.projectpoc.comment

import com.example.projectpoc.comment.model.Comment
import com.example.projectpoc.comment.model.CommentUtility

class CommentPresenter(commentVIew: CommentInterface.CommentVIew): CommentInterface.CommentPresenter{

    private val view: CommentInterface.CommentVIew=commentVIew
    private val model: CommentInterface.CommentModel= CommentUtility()

    override fun networkCallForComment(postId: Int?) {
        model.getCommentList(postId,this)
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