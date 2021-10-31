package com.example.harajtask.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harajtask.model.Item

class DetailsViewModel : ViewModel() {
    private val _item = MutableLiveData<Item>()
    val dataItem: LiveData<Item>
        get() = _item

    fun initData(data: Item) {
        _item.value = data
    }


    fun getTitle() = dataItem.value?.title.orEmpty()
    fun getBody() = dataItem.value?.body.orEmpty()
}