package com.example.projectpoc.post.postPresenter

import android.content.Context
import android.widget.Toast
import com.example.projectpoc.post.postDb.PostDbHelper
import com.example.projectpoc.post.postContract.PostInterface
import com.example.projectpoc.post.postModel.PostResponse
import com.example.projectpoc.post.postModel.PostApi
import com.example.projectpoc.post.postRepository.PostRepository
import com.example.projectpoc.utility.CheckInternet

class PostPresenter(var context: Context, postView: PostInterface.PostDataView) :
    PostInterface.PostPresenter {

    private val view: PostInterface.PostDataView = postView
    private val model: PostInterface.PostModel = PostApi()
    private val modelLocal: PostInterface.LocalDbPost = PostDbHelper(context)
    private lateinit var checkInternet: CheckInternet
    private val postRepository  = PostRepository()


    override fun getPostData(userId: Int?) {

        checkInternet = CheckInternet(context)

        if (checkInternet.isConnected()) {
           // postRepository.fetchDataFromNetwork(userId,this)
            model.getPostList(userId, this)
        } else {
            modelLocal.retrievePosts(this)
            Toast.makeText(context, "Mobile data is Off", Toast.LENGTH_SHORT).show()
        }
    }


    override fun handleSuccessResponse(postResponses: List<PostResponse>) {
        view.handleSuccess(postResponses)
      //  modelLocal.delData()
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