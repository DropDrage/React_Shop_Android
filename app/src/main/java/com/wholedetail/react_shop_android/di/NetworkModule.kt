package com.wholedetail.react_shop_android.di

import com.wholedetail.react_shop_android.BuildConfig
import com.wholedetail.react_shop_android.network.TShirtService
import com.wholedetail.react_shop_android.utils.retrofitconverters.EnumConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val T_SHIRT_PATH = "tShirts"

val networkModule = module {
    factory {
        OkHttpClient.Builder()
            .callTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
    factory<Retrofit> { params ->
        Retrofit.Builder()
            .baseUrl("${BuildConfig.serverUrl}/${params.get<String>()}/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(EnumConverterFactory())
            .build()
    }

    single {
        val retrofit: Retrofit = get { parametersOf(T_SHIRT_PATH) }
        return@single retrofit.create(TShirtService::class.java)
    }
}
