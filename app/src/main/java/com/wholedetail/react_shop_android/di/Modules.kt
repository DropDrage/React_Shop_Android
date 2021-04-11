package com.wholedetail.react_shop_android.di

import com.wholedetail.gramophone.account.AccountAuthenticator
import com.wholedetail.react_shop_android.BuildConfig
import com.wholedetail.react_shop_android.account.AccountManagementService
import com.wholedetail.react_shop_android.network.TShirtService
import com.wholedetail.react_shop_android.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

private const val T_SHIRT_PATH = "tShirt"

val accountModule = module {
    single { AccountAuthenticator(androidContext()) }
    single { AccountManagementService(androidContext()) }
}

val viewModelsModule = module {
    viewModel { HomeViewModel(get()) }
}

val networkModule = module {
    factory<Retrofit> { path ->
        Retrofit.Builder()
            .baseUrl("${BuildConfig.serverUrl}/$path/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(EnumConverterFactory())
            .build()
    }

    single {
        val retrofit: Retrofit = get { parametersOf(T_SHIRT_PATH) }
        return@single retrofit.create(TShirtService::class.java)
    }
}


class EnumConverterFactory : Converter.Factory() {
    override fun stringConverter(
        type: Type?, annotations: Array<out Annotation>?,
        retrofit: Retrofit?
    ): Converter<*, String>? {
        if (type is Class<*> && type.isEnum) {
            return Converter<Any?, String> { value -> (value as Enum<*>).name }
        }
        return null
    }
}