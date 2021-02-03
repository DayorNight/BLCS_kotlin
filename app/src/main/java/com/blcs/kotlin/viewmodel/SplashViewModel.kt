package com.blcs.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.blcs.kotlin.bean.User
import com.blcs.kotlin.repository.webservice.UserRepository

class SplashViewModel (
    savedStateHandle: SavedStateHandle,
    userRepository: UserRepository
) : ViewModel() {

    val userId: String =
        savedStateHandle["uid"] ?: throw IllegalArgumentException("missing user id")
    val user: LiveData<User> =  userRepository.getUser(userId)

}