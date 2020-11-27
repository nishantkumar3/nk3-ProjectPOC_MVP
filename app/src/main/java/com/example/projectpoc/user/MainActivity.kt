package com.example.projectpoc.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectpoc.R
import com.example.projectpoc.post.DashboardActivity
import com.example.projectpoc.sessionmanager.UserSessionManager
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), UserContract.UserView {

    private lateinit var userPresenter: UserPresenter
    private lateinit var loginButton: Button
    private lateinit var inputEmail: TextInputLayout
    private lateinit var userSessionManager: UserSessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        userPresenter = UserPresenter(applicationContext, this)
        userSessionManager = UserSessionManager(applicationContext)

        if (userSessionManager.isLoggedIn()) {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginButton = findViewById(R.id.loginButton)
        inputEmail = findViewById(R.id.email)

        loginButton.setOnClickListener {

            val emailId: String = inputEmail.editText?.text.toString().trim()
            userPresenter.handleLogin(emailId)
        }

    }

    override fun displayEmptyMessage(emptyMessage: String) {
        inputEmail.error = emptyMessage
    }

    override fun setEditTextNull() {
        inputEmail.error = null
    }

    override fun displayInvalidMessage(invalidMessage: String) {
        inputEmail.error = invalidMessage
    }

    override fun displayEmailNotFoundMessage(emailNotFound: String) {
        inputEmail.error = emailNotFound
    }


    override fun showFailureMessage(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()

    }

    override fun showResponseCode(responseCode: Int) {
        Toast.makeText(this@MainActivity, """Code : $responseCode""", Toast.LENGTH_SHORT).show()
    }

    override fun openDashBoard() {
        val intent = Intent(this@MainActivity, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }


}