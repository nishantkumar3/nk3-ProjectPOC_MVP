package com.example.projectpoc.comment.commentPresenter

import com.example.projectpoc.comment.commentContract.CommentInterface
import com.example.projectpoc.comment.commentModel.Comment
import com.example.projectpoc.comment.commentModel.CommentRepose

class CommentPresenter(commentVIew: CommentInterface.CommentVIew): CommentInterface.CommentPresenter{

    private val view: CommentInterface.CommentVIew=commentVIew
    private val model: CommentInterface.CommentModel= CommentRepose()

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