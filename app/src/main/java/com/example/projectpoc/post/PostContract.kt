package com.example.projectpoc.post

import com.example.projectpoc.post.postmodel.Post

interface PostContract {
    interface PostDataView {
        fun handleSuccess(postRespons: List<Post>)
        fun showFailureMessage(t: Throwable)
        fun showResponseCode(responseCode: Int)
    }

    interface PostPresenter {
        fun getPostData(userId: Int?)
        fun handleSuccessResponse(postRespons: List<Post>)
        fun handleFailure(t: Throwable)
        fun responseNotSuccessful(responseCode: Int)
        fun handlePostFromDb(postRespons: List<Post>)
    }

    interface PostModel {
        fun getPostList(userId: Int?, postPresenter: PostPresenter)

    }

    interface LocalDbPost {
        fun savePost(postRespons: List<Post>)
        fun retrievePosts(postPresenter: PostPresenter)
        fun delData()
    }
}