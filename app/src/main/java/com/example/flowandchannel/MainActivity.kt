package com.example.flowandchannel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var user: User

    @Inject
    lateinit var consumerProducer: ProducerConsumerChannel

    @Inject
    lateinit var producerConsumerFlow: ProducerConsumerFlow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // suspend dun example always return single object
//        CoroutineScope(Dispatchers.IO).launch {
//            user.getUsers().forEach {
//                Log.d("TAG", "User : ${it} ")
//            }
//        }
        // channel example returning stream
//        CoroutineScope(Dispatchers.IO).launch {
//            consumerProducer.produceData()
//        }
//        CoroutineScope(Dispatchers.IO).launch {
//            consumerProducer.consumeData()
//        }
        // flow example returning stream


        // consumer 1
//        CoroutineScope(Dispatchers.Main).launch {
//            var data = producerConsumerFlow.produceData()
////                Log.d(TAG, "collectData: $data")
//            data
//                .flowOn(Dispatchers.IO)
//                .onStart {
//                    emit(0)
//                    Log.d("TAG", "onStart Run ")
//                }
//                .onCompletion {
//                    emit(-7)
//                    Log.d("TAG", "onCompleted Run: ")
//                }
//                .onEach {
//                    Log.d("TAG", "on Each : upcoming item is $it ")
//                }
//                .collect {
//
//                    Log.d("TAG", " ${Thread.currentThread().name}")
//                }
//        }

        // consumer 2
//         CoroutineScope(Dispatchers.IO).launch {
//            producerConsumerFlow.collectDataTwo()
//        }

        CoroutineScope(Dispatchers.Main).launch {

            produceNumbers()
                .map {
                    it.uppercase()
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    Log.d("TAG", " Collect at Thread ${Thread.currentThread().name} ,  ${it}")

                }
        }

//        CoroutineScope(Dispatchers.Main).launch {
//            produceNumbers()
//                .filter {
//                    it.contains('a')
//                }
//                .collect {
//                    delay(2500)
//                    Log.d("TAG", "Consumer 2: ${it}")
//                }
//        }

        Log.d("TAG", "onCreate my flow : ${produceNumbers()}")
    }


    private fun produceNumbers(): Flow<String> {
        val list = listOf("pakistan", "india", "america", "england", "bangladesh")
        return flow {
            list.forEach {
                delay(2500)
                Log.d("TAG", " Emit at Thread ${Thread.currentThread().name}")
                emit(it)
            }
        }
    }

}