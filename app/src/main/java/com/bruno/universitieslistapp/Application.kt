package com.bruno.universitieslistapp

import android.app.Application
import com.bruno.universitieslistapp.di.getApplicationModule
import com.bruno.universitieslistapp.di.getNetworkModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            allowOverride(false)
            modules(
                getApplicationModule(),
                getNetworkModule()
            )
        }
    }
}