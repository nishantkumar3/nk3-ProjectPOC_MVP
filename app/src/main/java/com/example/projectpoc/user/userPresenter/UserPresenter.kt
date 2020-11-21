package com.example.projectpoc.user.userPresenter

import android.content.Context
import com.example.projectpoc.interfaces.Constant
import com.example.projectpoc.user.userContract.UserInterface
import com.example.projectpoc.user.userModel.User
import com.example.projectpoc.user.userModel.UserRepose
import com.example.projectpoc.sessionManager.UserSessionManager

class UserPresenter(var context: Context,userView: UserInterface.UserView): UserInterface.UserPresenter {

    private var view: UserInterface.UserView = userView
    private var model : UserInterface.UserModel = UserRepose()
    private lateinit var userSessionManager : UserSessionManager

    override fun networkCallForUser(emailId : String) {
        model.getUser(emailId,this)
    }

    override fun validateEmail(emailId: String):Boolean {
        var isValid = false
        if (emailId.isEmpty()) {
            view.displayEmptyMessage(Constant.EMPTY_MESSAGE)
        } else if (emailId.matches(Constant.emailPattern.toRegex())) {
            view.setEditTextNull()
            isValid = true
        } else {
            view.displayInvalidMessage(Constant.INVALID_MESSAGE)
        }
        return isValid
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
//            val intent = Intent(context, DashboardActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            context.startActivity(intent)
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


}