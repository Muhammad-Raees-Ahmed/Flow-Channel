package com.example.flowandchannel

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProducerConsumer @Inject constructor() : Any() {

    private val channel = Channel<String>()


    suspend fun produceData(): Channel<String> {
        channel.send("Raees")
        channel.send("Ali")
        channel.send("Ahmed")
        return channel
    }

    suspend fun consumeData(){
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TAG", "consumeData: ${channel.receive()}")
            Log.d("TAG", "consumeData: ${channel.receive()}")
            Log.d("TAG", "consumeData: ${channel.receive()}")
        }
    }
}