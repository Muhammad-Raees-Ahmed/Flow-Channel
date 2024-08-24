package com.example.flowandchannel

import kotlinx.coroutines.delay

class User:Any() {
    suspend fun getUsers(): List<String> {
        var names = mutableListOf<String>()
        names.add(getUserName(1))
        names.add(getUserName(2))
        names.add(getUserName(3))
        names.add(getUserName(4))
        return names
    }

     suspend fun getUserName(id: Int): String {
        delay(1000)
        return "user${id}"
    }
}