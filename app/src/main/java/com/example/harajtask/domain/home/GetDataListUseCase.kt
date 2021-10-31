package com.example.harajtask.domain.home

import androidx.annotation.WorkerThread
import com.example.harajtask.data.home.HomeRepository
import com.example.harajtask.domain.base.BaseUseCase
import com.example.harajtask.model.Item
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDataListUseCase(
    private val repository: HomeRepository,
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseUseCase.WithOutParams<List<Item>>() {

    @WorkerThread
    override suspend fun run(): List<Item> {
        return withContext(backgroundDispatcher) {
            return@withContext repository.getDataFromAsset()
        }
    }

}