package com.example.projectpoc.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectpoc.R
import com.example.projectpoc.sessionManager.UserSessionManager
import com.example.projectpoc.comment.CommentFragment
import com.example.projectpoc.constants.Constant
import com.example.projectpoc.post.postmodel.Post


class PostFragment : Fragment(), PostContract.PostDataView, PostCellClickListener {

    private lateinit var postPresenter: PostPresenter
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
        postPresenter = PostPresenter(activity?.applicationContext!!, this)

        val userId: Int = userSessionManager.getUserId()

        postPresenter.getPostData(userId)

        return view
    }


    override fun handleSuccess(postRespons: List<Post>) {
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.adapter = PostAdapter(postRespons, this)

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
        bundle.putInt(Constant.POST_ID, id)
        fragment.arguments = bundle

        fragmentTransaction?.replace(R.id.fragment_container, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }


}