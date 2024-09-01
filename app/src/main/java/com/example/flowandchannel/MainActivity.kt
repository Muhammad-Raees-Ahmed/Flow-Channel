package com.example.flowandchannel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

            producerConsumerFlow.produceDataName()

        // consumer 1
         CoroutineScope(Dispatchers.IO).launch {
            producerConsumerFlow.collectDataName()
        }

        // consumer 2
//         CoroutineScope(Dispatchers.IO).launch {
//            producerConsumerFlow.collectDataTwo()
//        }
    }

}