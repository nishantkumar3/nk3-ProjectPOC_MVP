package com.example.projectpoc.post.postContract

import com.example.projectpoc.post.localDbForPost.PostEntity
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
        fun loadPostFromDb()
        fun handlePostFromDb(posts: List<Post>)
       // fun savePostToDb(posts: List<Post>)
    }

    interface PostModel {
        fun getPostList(userId: Int?,postPresenter: PostPresenter)

    }

    interface LocalDbPost{
        fun savePost(posts: List<Post>)
        fun retrievePosts(postPresenter: PostPresenter)
        fun delData()
    }
}