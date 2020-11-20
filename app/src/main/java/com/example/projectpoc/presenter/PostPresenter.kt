package com.example.projectpoc.presenter

import com.example.projectpoc.interfaces.PostInterface
import com.example.projectpoc.model.dataModel.Post
import com.example.projectpoc.model.repose.PostRepose

class PostPresenter(postView: PostInterface.PostDataView):PostInterface.PostPresenter {

    private val view : PostInterface.PostDataView = postView
    private val model: PostInterface.PostModel = PostRepose()
    override fun networkCallForPost(userId : Int?) {
        model.getPostList(userId,this)
    }

    override fun handleSuccessResponse(posts: List<Post>) {
        view.handleSuccess(posts)
    }

    override fun handleFailure(t: Throwable) {
        view.showFailureMessage(t)
    }

    override fun responseNotSuccessful(responseCode: Int) {
        view.showResponseCode(responseCode)
    }
}