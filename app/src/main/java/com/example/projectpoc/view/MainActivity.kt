package com.example.projectpoc.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.projectpoc.R
import com.example.projectpoc.interfaces.Constant
import com.example.projectpoc.interfaces.UserInterface
import com.example.projectpoc.model.dataModel.User
import com.example.projectpoc.model.repose.UserRepose
import com.example.projectpoc.presenter.UserPresenter
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), UserInterface.UserView {
    private var presenter: UserPresenter? = null
    private lateinit var loginButton: Button
    private lateinit var inputEmail: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = UserPresenter(this)

        loginButton = findViewById(R.id.loginButton)
        inputEmail = findViewById(R.id.email)
        loginButton.setOnClickListener {

            val emailId: String = inputEmail.editText?.text.toString().trim()
            if (presenter?.validateEmail(emailId)!!) {
                presenter?.networkcall(emailId)
            }

        }

    }


    override fun isEmailValid(emailId: String): Boolean {
        var isValid = false
        if (emailId.isEmpty()) {
            inputEmail.error = getString(R.string.emailErrorMessage)
        } else if (emailId.matches(Constant.emailPattern.toRegex())) {
            inputEmail.error = null
            isValid = true
        } else {
            inputEmail.error = getString(R.string.invalidEmail)
        }
        return isValid
    }

    override fun handleSuccess(users: List<User>) {

        val emailEntered: String = inputEmail.editText?.text.toString()
        var userId = 0
        for (i: Int in users.indices)
            if (users[i].email.equals(emailEntered, true)) {
                userId = users[i].id
            }

        if (userId != 0) {
            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
            intent.putExtra("USERID",userId)
            startActivity(intent)
        } else {
            inputEmail.error = getString(R.string.emailNotFound)
        }
    }

    override fun showFailureMessage(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()

    }

    override fun showResponseCode(responseCode: Int) {
        Toast.makeText(this@MainActivity, """Code : $responseCode""", Toast.LENGTH_SHORT).show()
    }
}