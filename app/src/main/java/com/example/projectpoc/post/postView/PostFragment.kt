package com.example.projectpoc.post.postView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectpoc.R
import com.example.projectpoc.post.postAdapter.PostAdapter
import com.example.projectpoc.post.postCellClickListener.PostCellClickListener
import com.example.projectpoc.post.postContract.PostInterface
import com.example.projectpoc.post.postModel.PostResponse
import com.example.projectpoc.post.postPresenter.PostPresenter
import com.example.projectpoc.sessionManager.UserSessionManager
import com.example.projectpoc.comment.commentView.CommentFragment


class PostFragment : Fragment(), PostInterface.PostDataView, PostCellClickListener {

    private var presenter: PostPresenter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var userSessionManager: UserSessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_post, container, false)
        recyclerView = view.findViewById(R.id.postRecyclerView)
        userSessionManager = UserSessionManager(activity?.applicationContext!!)
        presenter = PostPresenter(activity?.applicationContext!!, this)

        val userId: Int = userSessionManager.getUserDetails()

          presenter?.getPostData(userId)
        return view
    }


    override fun handleSuccess(postResponses: List<PostResponse>) {
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.adapter = PostAdapter(postResponses, this)

    }

    override fun showFailureMessage(t: Throwable) {
        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun showResponseCode(responseCode: Int) {
        Toast.makeText(context, """Code : $responseCode""", Toast.LENGTH_SHORT).show()
    }

    override fun onCellClickListener(id: Int) {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val fragment = CommentFragment()

        val bundle = Bundle()
        bundle.putInt("POST_ID", id)
        fragment.arguments = bundle

        fragmentTransaction?.replace(R.id.fragment_container, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }


}