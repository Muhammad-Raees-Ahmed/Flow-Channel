package com.example.flowandchannel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var user: User

    @Inject
    lateinit var consumerProducer: ProducerConsumerChannel

    @Inject
    lateinit var shareFlowConsumerProducer: ShareFlowConsumerProducer

    @Inject
    lateinit var producerConsumerFlow: ProducerConsumerFlow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // suspend fun example always return single object
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

//        CoroutineScope(Dispatchers.Main).launch {
//
//            try {
//                produceNumbers()
//                    .map {
//                        it.uppercase()
//                    }
//                    .filter {
//                        delay(1000)
//                        it.contains('A')
//
//                    }
//                    .flowOn(Dispatchers.IO)
//                    .collect {
//
//                        Log.d("TAG", " Collect at Thread ${Thread.currentThread().name} ,  ${it}")
//
//                    }
//            } catch (e: Exception) {
//                Log.d("TAG", "Error : $e ")
//            }
//
//
//        }

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

        CoroutineScope(Dispatchers.Main).launch {
            produceCities()
            .collect {
                Log.d("TAG", "Shared Flow 1: $it ")
            }

        }
        CoroutineScope(Dispatchers.Main).launch {
            produceCities()
                .collect {
                    delay(1000)
                    Log.d("TAG", "Shared Flow 2 : $it ")
                }

        }

//        Log.d("TAG", "onCreate my flow : ${produceNumbers()}")
    }

    suspend fun produceCities(): Flow<String> {
        // MutableSharedFlow is hot nature
        val mutableSharedFlow = MutableSharedFlow<String>(2)
        CoroutineScope(Dispatchers.IO).launch {



            val list = listOf("london", "gaza", "germany")

            list.forEach {
                mutableSharedFlow.emit(it)
//                Log.d("TAG", "produceCities: $it")
//                delay(1000)
            }
        }
        return mutableSharedFlow
    }


    private fun produceNumbers(): Flow<String> {
        val list = listOf("pakistan", "india", "america", "england", "bangladesh")
        return flow {
            list.forEach {
                delay(2500)
                Log.d("TAG", " Emit at Thread ${Thread.currentThread().name} , $it")
                emit(it)
                throw Exception("Error Agya hy !")
            }
        }.catch {
            Log.d("TAG", "Error at producers: ")
            emit("error aya hy")
        }

    }

}