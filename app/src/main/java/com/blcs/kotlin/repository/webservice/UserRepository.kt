package com.blcs.kotlin.repository.webservice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blcs.kotlin.bean.User

/**
 * 获取数据
 */
class UserRepository {
//    private val webservice: Webservice = TODO()
    // ...
    fun getUser(userId: String): LiveData<User> {
        // This isn't an optimal implementation. We'll fix it later.
        val data = MutableLiveData<User>()
//        webservice.getUser(userId).enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                data.value = response.body()
//            }
//            // Error case is left out for brevity.
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                TODO()
//            }
//        })
        return data
    }
}