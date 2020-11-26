package com.example.projectpoc.post

import com.example.projectpoc.post.model.PostResponse

interface PostContract {
    interface PostDataView {
        fun handleSuccess(postResponses: List<PostResponse>)
        fun showFailureMessage(t:Throwable)
        fun showResponseCode(responseCode: Int)
    }

    interface PostPresenter {
        fun getPostData(userId: Int?)
        fun handleSuccessResponse(postResponses: List<PostResponse>)
        fun handleFailure(t: Throwable)
        fun responseNotSuccessful(responseCode : Int)
        fun handlePostFromDb(postResponses: List<PostResponse>)
    }

    interface PostModel {
        fun getPostList(userId: Int?,postPresenter: PostPresenter)

    }

    interface LocalDbPost{
        fun savePost(postResponses: List<PostResponse>)
        fun retrievePosts(postPresenter: PostPresenter)
        fun delData()
    }
}