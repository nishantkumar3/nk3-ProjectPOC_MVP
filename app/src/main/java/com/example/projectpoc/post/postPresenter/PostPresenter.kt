package com.example.projectpoc.post.postPresenter

import android.content.Context
import android.widget.Toast
import com.example.projectpoc.post.localDbForPost.LocalDbRepose
import com.example.projectpoc.post.postContract.PostInterface
import com.example.projectpoc.post.postModel.Post
import com.example.projectpoc.post.postModel.PostRepose
import com.example.projectpoc.utility.CheckInternet

class PostPresenter(var context: Context, postView: PostInterface.PostDataView) :
    PostInterface.PostPresenter {

    private val view: PostInterface.PostDataView = postView
    private val model: PostInterface.PostModel = PostRepose()
    private val modelLocal: PostInterface.LocalDbPost = LocalDbRepose(context)
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


    override fun handlePostFromDb(posts: List<Post>) {
        view.handleSuccess(posts)
    }

}