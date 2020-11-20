package com.example.projectpoc.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectpoc.R
import com.example.projectpoc.adapter.PostAdapter
import com.example.projectpoc.clickListener.PostCellClickListener
import com.example.projectpoc.interfaces.PostInterface
import com.example.projectpoc.model.dataModel.Post
import com.example.projectpoc.presenter.PostPresenter
import com.example.projectpoc.sessionManager.UserSessionManager


class PostFragment : Fragment(),PostInterface.PostDataView,PostCellClickListener {

    private var presenter : PostPresenter?= null
    private lateinit var recyclerView: RecyclerView
    private lateinit var userSessionManager: UserSessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_post, container, false)
        recyclerView = view.findViewById(R.id.postRecyclerView)
        userSessionManager= UserSessionManager(activity?.applicationContext!!)
       // val userId: Int? = arguments?.getInt("USER_ID")
       // Toast.makeText(context, userId.toString(), Toast.LENGTH_SHORT).show()
        val userId: Int= userSessionManager.getUserDetails()
        presenter = PostPresenter(this)
        presenter?.networkCallForPost(userId)
        return view
    }


    override fun handleSuccess(posts: List<Post>) {
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.adapter = PostAdapter(posts,this)

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

        val bundle= Bundle()
        bundle.putInt("POST_ID",id)
        fragment.arguments = bundle

        fragmentTransaction?.replace(R.id.fragment_container, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }


}