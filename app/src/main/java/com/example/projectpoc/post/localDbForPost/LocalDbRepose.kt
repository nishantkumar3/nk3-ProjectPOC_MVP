package com.example.projectpoc.post.localDbForPost

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.projectpoc.post.postContract.PostInterface
import com.example.projectpoc.post.postModel.Post

class LocalDbRepose(var context: Context) : PostInterface.LocalDbPost {

    private val db = PostRoomDb.getDatabase(context)

    override fun savePost(posts: List<Post>) {
        Log.d("Nishant", "Saving post")
        InsertData(posts, context as Application,db).execute()
    }

    override fun retrievePosts(postPresenter: PostInterface.PostPresenter) {
        Log.d("Nishant", "Fetching Post from db")
         FetchData(context as Application, postPresenter,db).execute()
    }

    override fun delData() {
        DeletePost(context as Application,db).execute()
    }

    class InsertData(var posts: List<Post>, var application: Application,var db: PostRoomDb) :
        AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {

            Log.d("Nishant", "Inserting data")
            val items = ArrayList<PostEntity>()
          //  val db = PostRoomDb.getDatabase(application)

            for (post in posts) {
                val item = PostEntity()
                item.id = post.id
                item.title = post.title
                item.body = post.body

                items.add(item)
            }

            db.postDao().insertPost(items)
            Log.d("Nishant", "Successfully inserted")
            return null
        }

    }

    class FetchData(var application: Application, var postPresenter: PostInterface.PostPresenter,var db: PostRoomDb) :
        AsyncTask<Void, Void, List<Post>>(){
        override fun doInBackground(vararg params: Void?): List<Post> {
            val postsFromDb: List<PostEntity>?
          //  val db = PostRoomDb.getDatabase(application)
            postsFromDb = db.postDao().fetchAllPosts()

            val items = ArrayList<Post>()
            for (postDb in postsFromDb) {
                val item = Post(postDb.id, postDb.title!!, postDb.body!!)

                items.add(item)
            }
            return items
        }

        override fun onPostExecute(result: List<Post>?) {
            if (result != null) {
                postPresenter.handlePostFromDb(result)
            }

        }
    }

    class DeletePost(var application: Application,var db: PostRoomDb) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            Log.d("Nishant", "Deleting Data")
             db = PostRoomDb.getDatabase(application)
            db.postDao().deleteAll()
            return null
        }

    }
}