package com.example.harajtask.data.home

import com.example.harajtask.model.Item

interface HomeRepository {
    suspend fun getDataFromAsset(): List<Item>
}