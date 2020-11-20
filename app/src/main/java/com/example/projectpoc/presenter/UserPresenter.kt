package com.example.projectpoc.presenter

import android.provider.Settings.Global.getString
import com.example.projectpoc.R
import com.example.projectpoc.interfaces.Constant
import com.example.projectpoc.interfaces.UserInterface
import com.example.projectpoc.model.dataModel.User
import com.example.projectpoc.model.repose.UserRepose

class UserPresenter(userView: UserInterface.UserView):UserInterface.UserPresenter {

    private var view: UserInterface.UserView = userView
    private var model : UserInterface.UserModel = UserRepose()

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

    override fun handleSuccessResponse(users: List<User>) {
        view.handleSuccess(users)
    }

    override fun handleFailure(t: Throwable) {
        view.showFailureMessage(t)
    }

    override fun responseNotSuccessful(responseCode: Int) {
        view.showResponseCode(responseCode)
    }


}