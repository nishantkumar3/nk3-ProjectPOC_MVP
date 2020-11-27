package com.example.projectpoc.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectpoc.R
import com.example.projectpoc.comment.commentmodel.Comment
import com.example.projectpoc.constants.Constant


class CommentFragment : Fragment(), CommentInterface.CommentVIew {

    private var presenter: CommentPresenter? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_comment, container, false)
        recyclerView = view.findViewById(R.id.commentRecyclerView)

        presenter = CommentPresenter(this)


        var postId: Int? = 0
        if (arguments?.getInt(Constant.POST_ID) != null) {
            postId = arguments?.getInt(Constant.POST_ID)
        }

        presenter?.networkCallForComment(postId)

        return view
    }

    override fun handleSuccess(comments: List<Comment>) {
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.adapter = CommentAdapter(comments)
    }

    override fun showFailureMessage(t: Throwable) {
        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun showResponseCode(responseCode: Int) {
        Toast.makeText(context, """Code : $responseCode""", Toast.LENGTH_SHORT).show()
    }


}