package com.example.harajtask

import androidx.multidex.MultiDexApplication
import com.example.harajtask.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class HarajApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@HarajApplication)
            modules(
                appModule
            )
        }
    }

    companion object {
        var instance: HarajApplication? = null
            public set
    }

    fun getInstance(): HarajApplication? {
        return HarajApplication.instance
    }
}
