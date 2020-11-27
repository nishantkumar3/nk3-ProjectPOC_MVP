package com.example.projectpoc.post.postmodel.postroomdb

import android.content.Context
import android.os.AsyncTask
import com.example.projectpoc.post.PostContract
import com.example.projectpoc.post.postmodel.Post

class PostDbHelper(var context: Context) : PostContract.LocalDbPost {

    private val postRoomDb = PostRoomDb.getDatabase(context)

    override fun savePost(postRespons: List<Post>) {
        InsertData(postRespons, postRoomDb).execute()
    }

    override fun retrievePosts(postPresenter: PostContract.PostPresenter) {
        FetchData(postPresenter, postRoomDb).execute()
    }

    override fun delData() {
        DeletePost(postRoomDb).execute()
    }

    class InsertData(private var posts: List<Post>, private var postRoomDb: PostRoomDb) :
        AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {

            val postEntities = ArrayList<PostEntity>()

            for (post in posts) {
                val postEntity = PostEntity()
                postEntity.id = post.id
                postEntity.title = post.title
                postEntity.body = post.body

                postEntities.add(postEntity)
            }

            postRoomDb.postDao().insertPost(postEntities)
            return null
        }

    }


    class FetchData(
        private var postPresenter: PostContract.PostPresenter,
        private var postRoomDb: PostRoomDb
    ) :
        AsyncTask<Void, Void, List<Post>>() {
        override fun doInBackground(vararg params: Void?): List<Post> {
            val postsFromDb: List<PostEntity>?
            postsFromDb = postRoomDb.postDao().fetchAllPosts()

            val posts = ArrayList<Post>()
            for (postDb in postsFromDb) {

                val post = Post(postDb.id, postDb.title!!, postDb.body!!)

                posts.add(post)
            }
            return posts
        }

        override fun onPostExecute(posts: List<Post>?) {
            if (posts != null) {
                postPresenter.handlePostFromDb(posts)
            }


        }
    }

    class DeletePost(private var postRoomDb: PostRoomDb) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            postRoomDb.postDao().deleteAll()
            return null
        }

    }
}