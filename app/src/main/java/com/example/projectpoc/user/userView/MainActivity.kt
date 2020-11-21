package com.example.projectpoc.user.userView

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectpoc.R
import com.example.projectpoc.user.userContract.UserInterface
import com.example.projectpoc.user.userPresenter.UserPresenter
import com.example.projectpoc.sessionManager.UserSessionManager
import com.example.projectpoc.post.postView.DashboardActivity
import com.google.android.material.textfield.TextInputLayout

 class MainActivity : AppCompatActivity(), UserInterface.UserView {
    private var presenter: UserPresenter? = null
    private lateinit var loginButton: Button
    private lateinit var inputEmail: TextInputLayout
    private lateinit var userSessionManager: UserSessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = UserPresenter(applicationContext,this)
        userSessionManager = UserSessionManager(applicationContext)

        if(userSessionManager.isLoggedIn()){
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }


        loginButton = findViewById(R.id.loginButton)
        inputEmail = findViewById(R.id.email)
        loginButton.setOnClickListener {

            val emailId: String = inputEmail.editText?.text.toString().trim()
            if (presenter?.validateEmail(emailId)!!) {
                presenter?.networkCallForUser(emailId)
            }
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


    /* override fun handleSuccess(users: List<User>) {
         val emailEntered: String = inputEmail.editText?.text.toString()
         var userId = 0
         for (i: Int in users.indices)
             if (users[i].email.equals(emailEntered, true)) {
                 userId = users[i].id
             }
         if (userId != 0) {
             userSessionManager.createLoginSession(userId)
             val intent = Intent(this@MainActivity, DashboardActivity::class.java)
             startActivity(intent)
             finish()
         } else {
             inputEmail.error = getString(R.string.emailNotFound)
         }
     }*/

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