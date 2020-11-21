package com.example.projectpoc.post.postContract

import com.example.projectpoc.post.postModel.Post

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