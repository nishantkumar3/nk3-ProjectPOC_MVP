package com.example.projectpoc.user

import com.example.projectpoc.user.model.User

interface UserContract {

    interface UserModel {
        fun getUser(emailId: String, presenter: UserPresenter)
    }

    interface UserView {
        fun displayEmptyMessage(emptyMessage: String)
        fun setEditTextNull()
        fun displayInvalidMessage(invalidMessage: String)
        fun displayEmailNotFoundMessage(emailNotFound: String)
        fun showFailureMessage(t: Throwable)
        fun showResponseCode(responseCode: Int)
        fun openDashBoard()
    }

    interface UserPresenter {
        fun handleLogin(emailId: String)
        fun handleSuccessResponse(users: List<User>, emailId: String)
        fun handleFailure(t: Throwable)
        fun responseNotSuccessful(responseCode: Int)

    }
}