package com.example.projectpoc.presenter

import com.example.projectpoc.interfaces.UserInterface
import com.example.projectpoc.model.dataModel.User
import com.example.projectpoc.model.repose.UserRepose

class UserPresenter(userView: UserInterface.UserView):UserInterface.UserPresenter {

    private var view: UserInterface.UserView = userView
    private var model : UserInterface.UserModel = UserRepose()

    override fun networkcall(emailId : String) {
        model.getUser(emailId,this)
    }

    override fun validateEmail(emailId: String):Boolean {
       return view.isEmailValid(emailId)
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