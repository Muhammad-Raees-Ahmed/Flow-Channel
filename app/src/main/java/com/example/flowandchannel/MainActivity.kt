package com.example.flowandchannel

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            getUsers().forEach {
                Log.d(TAG, "User : ${it} ")
            }
        }

    }

    suspend fun getUsers(): List<String> {
        var names = mutableListOf<String>()
        names.add(getUserName(1))
        names.add(getUserName(2))
        names.add(getUserName(3))
        names.add(getUserName(4))
        return names
    }

    private suspend fun getUserName(id: Int): String {
        delay(1000)
        return "user${id}"
    }
}