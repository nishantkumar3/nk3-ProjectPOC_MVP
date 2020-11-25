package com.example.projectpoc.post.postPresenter

import android.content.Context
import android.util.Log
import com.example.projectpoc.post.localDbForPost.LocalDbRepose
import com.example.projectpoc.post.postContract.PostInterface
import com.example.projectpoc.post.postModel.Post
import com.example.projectpoc.post.postModel.PostRepose

class PostPresenter(var context: Context,postView: PostInterface.PostDataView): PostInterface.PostPresenter {

    private val view : PostInterface.PostDataView = postView
    private val model: PostInterface.PostModel = PostRepose()
    private val modelLocal : PostInterface.LocalDbPost= LocalDbRepose(context)
    override fun networkCallForPost(userId : Int?) {
        model.getPostList(userId,this)
    }

    override fun handleSuccessResponse(posts: List<Post>) {
        view.handleSuccess(posts)
        modelLocal.delData()
        modelLocal.savePost(posts)
    }

    override fun handleFailure(t: Throwable) {
        view.showFailureMessage(t)
    }

    override fun responseNotSuccessful(responseCode: Int) {
        view.showResponseCode(responseCode)
    }

    override fun loadPostFromDb() {
        Log.d("Nishant", "on the way in presenter")
        modelLocal.retrievePosts(this)
    }

    override fun handlePostFromDb(posts: List<Post>) {
        view.handleSuccess(posts)
    }

//    override fun savePostToDb(posts: List<Post>) {
//        modelLocal.savePost(posts)
//    }
}