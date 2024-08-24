package com.example.flowandchannel

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ProducerConsumerFlow {


    suspend fun produceData(): Flow<Int> {
        val list = listOf(1, 2, 6, 4, 80)

        return flow {
            list.forEach {
                delay(1000)
                emit(it)
            }
        }
    }

    suspend fun collectData(){
        CoroutineScope(Dispatchers.IO).launch {
            var data=produceData()
            data.collect{
                Log.d("TAG", "collectData flow :  $it")
            }
        }
    }
}