package com.example.projectpoc.user

import android.content.Context
import com.example.projectpoc.constants.Constant
import com.example.projectpoc.sessionmanager.UserSessionManager
import com.example.projectpoc.user.model.User
import com.example.projectpoc.user.model.UserApi

class UserPresenter(var context: Context, userView: UserContract.UserView) :
    UserContract.UserPresenter {

    private var view: UserContract.UserView = userView
    private var userApi: UserContract.UserModel = UserApi()
    private lateinit var userSessionManager: UserSessionManager


    override fun handleLogin(emailId: String) {
        if (validateEmail(emailId))
            userApi.getUser(emailId, this)
    }


    override fun handleSuccessResponse(users: List<User>, emailId: String) {
        userSessionManager = UserSessionManager(context)
        var userId = 0
        for (i: Int in users.indices)
            if (users[i].email.equals(emailId, true)) {
                userId = users[i].id
            }
        if (userId != 0) {
            userSessionManager.createLoginSession(userId)
            view.openDashBoard()
        } else {
            view.displayEmailNotFoundMessage(Constant.EMAIL_NOT_FOUND)
        }
    }

    override fun handleFailure(t: Throwable) {
        view.showFailureMessage(t)
    }

    override fun responseNotSuccessful(responseCode: Int) {
        view.showResponseCode(responseCode)
    }

    private fun validateEmail(emailId: String): Boolean {
        var isValid = false
        when {
            emailId.isEmpty() -> view.displayEmptyMessage(Constant.EMPTY_MESSAGE)

            emailId.matches(Constant.emailPattern.toRegex()) -> {
                view.setEditTextNull()
                isValid = true
            }
            else -> view.displayInvalidMessage(Constant.INVALID_MESSAGE)
        }
        return isValid
    }
}