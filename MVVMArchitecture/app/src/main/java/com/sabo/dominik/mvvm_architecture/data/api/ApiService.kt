package com.sabo.dominik.mvvm_architecture.data.api

import com.sabo.dominik.mvvm_architecture.data.model.User
import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<User>>

}