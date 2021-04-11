package com.wholedetail.react_shop_android.account

import android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT
import android.app.Service
import android.content.Intent
import com.wholedetail.gramophone.account.AccountAuthenticator
import org.koin.android.ext.android.inject

class AccountAuthenticatorService : Service() {

    private val authenticator: AccountAuthenticator by inject()

    override fun onBind(intent: Intent) =
        if (intent.action == ACTION_AUTHENTICATOR_INTENT) authenticator.iBinder else null
}