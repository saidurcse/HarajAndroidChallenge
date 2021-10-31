package com.example.harajtask.di

import com.example.harajtask.data.home.HomeRepository
import com.example.harajtask.data.home.HomeRepositoryImpl
import com.example.harajtask.ui.details.DetailsViewModel
import com.example.harajtask.domain.home.GetDataListUseCase
import com.example.harajtask.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<HomeRepository> { HomeRepositoryImpl(androidContext()) }

    factory { GetDataListUseCase(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel() }
}