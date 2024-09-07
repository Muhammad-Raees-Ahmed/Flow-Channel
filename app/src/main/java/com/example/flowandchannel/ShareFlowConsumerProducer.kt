package com.example.flowandchannel

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class ShareFlowConsumerProducer @Inject constructor() {

    suspend fun produceCities(): Flow<String> {
        // MutableSharedFlow is hot nature

        val mutableSharedFlow = MutableSharedFlow<String>()
        val list = listOf("london", "gaza", "germany")

        list.forEach {
            mutableSharedFlow.emit(it)
            Log.d("TAG", "produceCities: $it")
            delay(1000)
        }
        return mutableSharedFlow
    }
}