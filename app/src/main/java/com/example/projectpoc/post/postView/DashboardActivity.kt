package com.example.projectpoc.post.postView

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.projectpoc.R
import com.example.projectpoc.post.postDb.PostDbHelper
import com.example.projectpoc.post.postContract.PostInterface
import com.example.projectpoc.sessionManager.UserSessionManager
import com.example.projectpoc.user.userView.MainActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var userSessionManager:UserSessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        userSessionManager=UserSessionManager(this)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = PostFragment()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val modelLocal: PostInterface.LocalDbPost = PostDbHelper(application)
        userSessionManager.logoutUser()
        modelLocal.delData()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        return super.onOptionsItemSelected(item)
    }


}