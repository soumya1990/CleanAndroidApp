package com.srm.cleanapp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

private const val MY_DELAY = 1 * 5000

class NumberAdder(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val currDelay: Int = MY_DELAY
) {

    suspend fun add(a: String, b: String): String {
        return withContext(dispatcher) {
            delay(currDelay.toLong())
            (a.toInt() + b.toInt()).toString()
        }
    }

    fun addTwo(a: String, b: String): Flow<String> {
        return flow {
            emit((a.toInt() + b.toInt()).toString())
        }.onEach {
            delay(currDelay.toLong())
        }.flowOn(dispatcher)
    }
}