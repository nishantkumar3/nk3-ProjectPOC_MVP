package com.example.projectpoc.interfaces

import com.example.projectpoc.model.dataModel.Post

interface PostInterface {
    interface PostDataView {
        fun handleSuccess(posts: List<Post>)
        fun showFailureMessage(t:Throwable)
        fun showResponseCode(responseCode: Int)
    }

    interface PostPresenter {
        fun networkCallForPost(userId: Int?)
        fun handleSuccessResponse(posts: List<Post>)
        fun handleFailure(t: Throwable)
        fun responseNotSuccessful(responseCode : Int)
    }

    interface PostModel {
        fun getPostList(userId: Int?,postPresenter: PostPresenter)
    }
}