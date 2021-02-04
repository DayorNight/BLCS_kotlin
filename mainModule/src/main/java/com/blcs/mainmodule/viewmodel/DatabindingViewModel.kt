package com.blcs.mainmodule.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.blcs.mainmodule.bean.User
import com.blcs.mainmodule.repository.webservice.UserRepository

class DatabindingViewModel (
    savedStateHandle: SavedStateHandle,
    userRepository: UserRepository
) : ViewModel() {

    val userId: String =
        savedStateHandle["uid"] ?: throw IllegalArgumentException("missing user id")
    val user: LiveData<User> =  userRepository.getUser(userId)

}