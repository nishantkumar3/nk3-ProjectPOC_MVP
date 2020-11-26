package com.example.projectpoc.post.model.postroomdb

import android.content.Context
import android.os.AsyncTask
import com.example.projectpoc.post.PostContract
import com.example.projectpoc.post.model.PostResponse

class PostDbHelper(var context: Context) : PostContract.LocalDbPost {

    private val db = PostRoomDb.getDatabase(context)

    override fun savePost(postResponses: List<PostResponse>) {
        InsertData(postResponses,db).execute()
    }

    override fun retrievePosts(postPresenter: PostContract.PostPresenter) {
         FetchData( postPresenter,db).execute()
    }

    override fun delData() {
        DeletePost(db).execute()
    }

    class InsertData(var postResponses: List<PostResponse>, var db: PostRoomDb) :
        AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {

            val postEntities = ArrayList<PostEntity>()

            for (post in postResponses) {
                val postEntity = PostEntity()
                postEntity.id = post.id
                postEntity.title = post.title
                postEntity.body = post.body

                postEntities.add(postEntity)
            }

            db.postDao().insertPost(postEntities)
            return null
        }

    }

    class FetchData(var postPresenter: PostContract.PostPresenter, var db: PostRoomDb) :
        AsyncTask<Void, Void, List<PostResponse>>(){
        override fun doInBackground(vararg params: Void?): List<PostResponse> {
            val postsFromDb: List<PostEntity>?
            postsFromDb = db.postDao().fetchAllPosts()

            val postResponses = ArrayList<PostResponse>()
            for (postDb in postsFromDb) {

                val postResponse= PostResponse(postDb.id, postDb.title!!, postDb.body!!)

                postResponses.add(postResponse)
            }
            return postResponses
        }

        override fun onPostExecute(result: List<PostResponse>?) {
            if (result != null) {
                postPresenter.handlePostFromDb(result)
            }

        }
    }

    class DeletePost(var db: PostRoomDb) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            db.postDao().deleteAll()
            return null
        }

    }
}