package com.example.projectpoc.model.repose

import android.util.Log
import com.example.projectpoc.interfaces.UserInterface
import com.example.projectpoc.model.api.RetrofitClient
import com.example.projectpoc.model.api.RetrofitInterface
import com.example.projectpoc.model.dataModel.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepose: UserInterface.UserModel {


    private val retrofitInterface:RetrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface::class.java)

    override fun getUser(emailId : String,presenter: UserInterface.UserPresenter){

        val call = retrofitInterface.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(!response.isSuccessful){
                    presenter.responseNotSuccessful(response.code())
                    return
                }
                response.body()?.let { presenter.handleSuccessResponse(it) }
              /*  if (response.isSuccessful) {
                    val users: List<User>? = response.body()
                    var userId = 0

                    if (users != null) {
                        for (i: Int in users.indices) {
                            if (users[i].email.equals(emailId, true)) {
                                userId = users[i].id
                                break
                            }
                        }

                    }
                    if (userId == 0) {
                        Log.d("Error","Email id not found")
                    }else{
                        Log.d("userEmail",emailId)
                        Log.d("userId",userId.toString())
                    }
                }*/
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                presenter.handleFailure(t)
            }
        })


    }


}