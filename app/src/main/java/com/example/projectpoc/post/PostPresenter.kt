package com.example.projectpoc.post

import android.content.Context
import android.widget.Toast
import com.example.projectpoc.post.model.PostApi
import com.example.projectpoc.post.model.PostResponse
import com.example.projectpoc.post.model.postroomdb.PostDbHelper
import com.example.projectpoc.utility.CheckInternet

class PostPresenter(var context: Context, postView: PostContract.PostDataView) :
    PostContract.PostPresenter {

    private val view: PostContract.PostDataView = postView
    private val model: PostContract.PostModel = PostApi()
    private val modelLocal: PostContract.LocalDbPost = PostDbHelper(context)
    private lateinit var checkInternet: CheckInternet


    override fun getPostData(userId: Int?) {

        checkInternet = CheckInternet(context)

        if (checkInternet.isConnected()) {
            model.getPostList(userId, this)
        } else {
            modelLocal.retrievePosts(this)
            Toast.makeText(context, "Mobile data is Off", Toast.LENGTH_SHORT).show()
        }
    }


    override fun handleSuccessResponse(postResponses: List<PostResponse>) {
        view.handleSuccess(postResponses)
        modelLocal.savePost(postResponses)
    }

    override fun handleFailure(t: Throwable) {
        view.showFailureMessage(t)
    }

    override fun responseNotSuccessful(responseCode: Int) {
        view.showResponseCode(responseCode)
    }


    override fun handlePostFromDb(postResponses: List<PostResponse>) {
        view.handleSuccess(postResponses)
    }

}