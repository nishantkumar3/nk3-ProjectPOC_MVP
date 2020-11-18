package com.example.projectpoc.interfaces

import com.example.projectpoc.model.dataModel.User

interface UserInterface {
    interface UserModel{
        fun getUser(emailId: String,presenter: UserInterface.UserPresenter)


    }
    interface UserView{
        fun isEmailValid(emailId: String):Boolean
        fun handleSuccess(users: List<User>)
        fun showFailureMessage(t:Throwable)
        fun showResponseCode(responseCode: Int)
    }
    interface UserPresenter{
        fun networkcall(emailId : String)
        fun validateEmail(emailId:String):Boolean
        fun handleSuccessResponse(users: List<User>)
        fun handleFailure(t: Throwable)
        fun responseNotSuccessful(responseCode : Int)

    }
}