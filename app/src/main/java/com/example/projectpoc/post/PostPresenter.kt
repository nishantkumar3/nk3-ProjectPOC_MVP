package com.example.projectpoc.post

import android.content.Context
import com.example.projectpoc.post.postmodel.PostApi
import com.example.projectpoc.post.postmodel.Post
import com.example.projectpoc.post.postmodel.postroomdb.PostDbHelper
import com.example.projectpoc.utility.CheckInternet

class PostPresenter(var context: Context, postView: PostContract.PostDataView) :
    PostContract.PostPresenter {

    private val view: PostContract.PostDataView = postView
    private val postApi: PostContract.PostModel = PostApi()
    private val postDbHelper: PostContract.LocalDbPost = PostDbHelper(context)
    private lateinit var checkInternet: CheckInternet


    override fun getPostData(userId: Int?) {

        checkInternet = CheckInternet(context)

        if (checkInternet.isConnected()) {
            postApi.getPostList(userId, this)
        } else {
            postDbHelper.retrievePosts(this)
        }
    }


    override fun handleSuccessResponse(postRespons: List<Post>) {
        view.handleSuccess(postRespons)
        postDbHelper.savePost(postRespons)
    }

    override fun handleFailure(t: Throwable) {
        view.showFailureMessage(t)
    }

    override fun responseNotSuccessful(responseCode: Int) {
        view.showResponseCode(responseCode)
    }


    override fun handlePostFromDb(postRespons: List<Post>) {
        view.handleSuccess(postRespons)
    }

}