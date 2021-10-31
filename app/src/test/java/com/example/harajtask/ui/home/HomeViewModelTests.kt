package com.example.harajtask.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.harajtask.domain.home.GetDataListUseCase
import com.example.harajtask.model.Item
import com.example.harajtask.utils.MainCoroutineRule
import com.example.harajtask.utils.getOrAwaitValue
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTests {
    private val getDataListUseCase: GetDataListUseCase =
        Mockito.mock(GetDataListUseCase::class.java)

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    /// Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Mock
    lateinit var dataListObserver: Observer<List<Item>>

    @Mock
    lateinit var filterItemsObserver: Observer<List<Item>>

    @Before
    fun setUp() {
        // create view model
        viewModel = HomeViewModel(getDataListUseCase)

        viewModel.dataItems.observeForever(dataListObserver)
        viewModel.filterItems.observeForever(filterItemsObserver)
    }

    @After
    fun tearDown() {
        reset(dataListObserver)
        reset(filterItemsObserver)
    }

    @Test
    fun `should get data from Asset`() {
        runBlocking {

            // given
            Mockito.`when`(getDataListUseCase.run()).thenReturn(mockData)

            // when
            viewModel.fetchData()

            // then
            val observeCapture = argumentCaptor<List<Item>>()
            viewModel.dataItems.getOrAwaitValue()
            verify(dataListObserver).onChanged(observeCapture.capture())

            Assert.assertEquals(
                mockData,
                observeCapture.firstValue
            )

        }
    }

    @Test
    fun `should set data from asset and filter data`() {
        runBlocking {

            // given
            Mockito.`when`(getDataListUseCase.run()).thenReturn(mockData)

            // when
            viewModel.fetchData()
            viewModel.filterData("one")

            // then
            val observeCapture = argumentCaptor<List<Item>>()
            viewModel.filterItems.getOrAwaitValue()
            verify(filterItemsObserver).onChanged(observeCapture.capture())

            Assert.assertEquals(
                listOf(mockData[0]),
                observeCapture.firstValue
            )
        }
    }

    private val mockData = listOf(
        Item(
            title = "Sample title one",
            username = "",
            thumbURL = "",
            commentCount = 0,
            city = " ",
            date = "",
            body = ""
        ),
        Item(
            title = "Sample title two",
            username = "",
            thumbURL = "",
            commentCount = 0,
            city = " ",
            date = "",
            body = ""
        ),
        Item(
            title = "Sample title three",
            username = "",
            thumbURL = "",
            commentCount = 0,
            city = " ",
            date = "",
            body = ""
        ),
        Item(
            title = "Sample title four",
            username = "",
            thumbURL = "",
            commentCount = 0,
            city = " ",
            date = "",
            body = ""
        )
    )
}