package com.sabo.dominik.mvvm_architecture.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getUsers() = apiService.getUsers()

}