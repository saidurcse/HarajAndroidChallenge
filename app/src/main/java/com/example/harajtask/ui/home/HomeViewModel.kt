package com.example.harajtask.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harajtask.domain.home.GetDataListUseCase
import com.example.harajtask.model.Item
import kotlinx.coroutines.launch

class HomeViewModel(private val getDataListUseCase: GetDataListUseCase) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _items = MutableLiveData<List<Item>>()
    val dataItems: LiveData<List<Item>>
        get() = _items

    private val _filterItems = MutableLiveData<List<Item>>()
    val filterItems: LiveData<List<Item>>
        get() = _filterItems

    fun fetchData() {
        _dataLoading.value = true
        viewModelScope.launch {
            _items.value = getDataListUseCase.run()
            _dataLoading.value = false
        }
    }

    fun filterData(queryData: String) {
        if (queryData.isNotEmpty()) {
            _filterItems.value = _items.value?.filter {
                it.title.contains(queryData) || it.body.contains(queryData)
            }
        } else _filterItems.value = _items.value
    }

}