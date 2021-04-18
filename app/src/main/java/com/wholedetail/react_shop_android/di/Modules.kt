package com.wholedetail.react_shop_android.di

import com.wholedetail.react_shop_android.account.AccountAuthenticator
import com.wholedetail.react_shop_android.account.AccountManagementService
import com.wholedetail.react_shop_android.ui.TShirtFragment
import com.wholedetail.react_shop_android.ui.TShirtViewModel
import com.wholedetail.react_shop_android.ui.home.HomeFragment
import com.wholedetail.react_shop_android.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {
    single { AccountAuthenticator(androidContext()) }
    single { AccountManagementService(androidContext()) }
}

val viewModelsModule = module {
    scope<HomeFragment> {
        viewModel { HomeViewModel(get()) }
    }

    scope<TShirtFragment> {
        viewModel { TShirtViewModel(get(), get()) } //ToDo test withot params
    }
}
