package com.example.projectpoc.interfaces

interface Constant {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        const val EMPTY_MESSAGE = "Email field cannot be empty."
        const val INVALID_MESSAGE="Email is invalid"
    }
}