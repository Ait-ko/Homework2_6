package com.example.homework2_6

import android.app.Application
import com.example.di.CartoonModule.cartoonModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@HiltAndroidApp
class App :Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
            modules(cartoonModule)
        }
    }
}