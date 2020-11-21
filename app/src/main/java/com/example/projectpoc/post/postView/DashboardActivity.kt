package com.example.projectpoc.post.postView

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.projectpoc.R
import com.example.projectpoc.post.postView.PostFragment
import com.example.projectpoc.sessionManager.UserSessionManager

class DashboardActivity : AppCompatActivity() {

    private lateinit var userSessionManager:UserSessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        Log.d("nish","in DashboardActivity")
        userSessionManager=UserSessionManager(this)

      //  val userId : Int = intent.getIntExtra("USER_ID",0)
       // Toast.makeText(this,userId.toString() , Toast.LENGTH_SHORT).show()



        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = PostFragment()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()

//        val userBundle = Bundle()
//        userBundle.putInt("USER_ID",userId)
//        fragment.arguments = userBundle
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        userSessionManager.logoutUser()
        return super.onOptionsItemSelected(item)
    }
}