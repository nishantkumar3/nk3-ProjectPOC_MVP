package com.example.projectpoc.presenter

import com.example.projectpoc.interfaces.CommentInterface
import com.example.projectpoc.model.dataModel.Comment
import com.example.projectpoc.model.repose.CommentRepose

class CommentPresenter(commentVIew: CommentInterface.CommentVIew): CommentInterface.CommentPresenter{

    private val view:CommentInterface.CommentVIew=commentVIew
    private val model:CommentInterface.CommentModel=CommentRepose()

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