package com.example.projectpoc.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectpoc.R
import com.example.projectpoc.post.postmodel.Post

class PostAdapter(
    private val posts: List<Post>,
    private val postCellClickListener: PostCellClickListener
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.postId)
        val title: TextView = itemView.findViewById(R.id.postTitle)
        val body: TextView = itemView.findViewById(R.id.postBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.single_post_row, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.id.text = posts[position].id.toString()
        holder.title.text = posts[position].title
        holder.body.text = posts[position].body


        holder.itemView.setOnClickListener {
            postCellClickListener.onCellClickListener(posts[position].id)
        }

    }

    override fun getItemCount(): Int {
        return posts.size
    }
}