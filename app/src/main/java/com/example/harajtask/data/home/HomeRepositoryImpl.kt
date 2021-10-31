package com.example.harajtask.data.home

import android.content.Context
import com.example.harajtask.model.Item
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepositoryImpl constructor(
    private val context: Context,
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) : HomeRepository {

    override suspend fun getDataFromAsset(): List<Item> {
        return withContext(backgroundDispatcher) {
            val data = loadJSONFromAsset()
            return@withContext Gson().fromJson(data, Array<Item>::class.java).toList()
        }
    }

    private fun loadJSONFromAsset(): String =
        context.assets.open(DATA_FILE_NAME)
            .bufferedReader()
            .use { it.readText() }

    companion object {
        private const val DATA_FILE_NAME = "data.json"
    }

}