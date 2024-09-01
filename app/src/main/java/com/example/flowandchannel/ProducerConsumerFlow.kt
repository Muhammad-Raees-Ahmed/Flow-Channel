package com.example.flowandchannel

import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProducerConsumerFlow @Inject constructor() {


     fun produceData(): Flow<Int> {
        val list = listOf(1, 2, 6, 4, 80,6,3,7,8)

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
            data
                .onStart {
                    emit(0)
                    Log.d("TAG", "onStart Run ")
                }
                .onCompletion {
                    emit(-7)
                    Log.d("TAG", "onCompleted Run: ")
                }
                .onEach {
                    Log.d("TAG", "on Each : upcoming item is $it ")
                }
                .collect{

                Log.d("TAG", "collectData flow consumer 1:  $it")
            }
        }
    }

    suspend fun collectDataTwo(){
        CoroutineScope(Dispatchers.IO).launch {
            var data=produceData()
            data.collect{
                delay(2500)
                Log.d("TAG", "collectData flow  consumer 2:  $it")
            }
        }
    }
}