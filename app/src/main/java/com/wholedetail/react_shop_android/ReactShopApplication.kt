package com.wholedetail.react_shop_android

import android.app.Application
import com.wholedetail.react_shop_android.di.accountModule
import com.wholedetail.react_shop_android.di.networkModule
import com.wholedetail.react_shop_android.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ReactShopApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ReactShopApplication)
            // declare modules
            modules(networkModule, accountModule, viewModelsModule)
        }
    }
}