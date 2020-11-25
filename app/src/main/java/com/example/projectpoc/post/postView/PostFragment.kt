package com.example.projectpoc.post.postView

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.projectpoc.post.postModel.Post
import com.example.projectpoc.post.postPresenter.PostPresenter
import com.example.projectpoc.sessionManager.UserSessionManager
import com.example.projectpoc.comment.commentView.CommentFragment
import com.example.projectpoc.utility.CheckInternet


class PostFragment : Fragment(), PostInterface.PostDataView, PostCellClickListener {

    private var presenter: PostPresenter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var userSessionManager: UserSessionManager
    private lateinit var checkInternet: CheckInternet
    private val TAG = "PostFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("nish", "In postFragment")
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_post, container, false)
        recyclerView = view.findViewById(R.id.postRecyclerView)
        userSessionManager = UserSessionManager(activity?.applicationContext!!)

        val userId: Int = userSessionManager.getUserDetails()

        presenter = PostPresenter(activity?.applicationContext!!, this)
        checkInternet = CheckInternet(activity?.applicationContext!!)

        if (checkInternet.isConnected()) {
            presenter?.networkCallForPost(userId)
        } else {
            presenter?.loadPostFromDb()
            Toast.makeText(context, "Mobile data is Off", Toast.LENGTH_SHORT).show()
        }



        return view
    }


    override fun handleSuccess(posts: List<Post>) {
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.adapter = PostAdapter(posts, this)

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach() called with: context = $context")
    }

}