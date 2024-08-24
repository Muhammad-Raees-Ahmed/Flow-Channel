package com.example.flowandchannel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // suspend dun example always return single object
        user=User()
        CoroutineScope(Dispatchers.IO).launch {
            user.getUsers().forEach {
                Log.d("TAG", "User : ${it} ")
            }
        }

    }


}