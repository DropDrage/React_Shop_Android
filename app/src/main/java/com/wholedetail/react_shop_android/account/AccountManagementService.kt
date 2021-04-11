package com.wholedetail.react_shop_android.account

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import com.wholedetail.gramophone.account.credentials.UserCredentials
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

const val USER_ACCOUNT_TYPE = "user"
const val AUTH_TOKEN_TYPE = "auth_token"

class AccountManagementService constructor(context: Context) {

    private val accountManager = AccountManager.get(context)
    private val account get() = accountManager.accounts.firstOrNull { it.type == USER_ACCOUNT_TYPE }

    private val userLoggedStatusChanged = MutableStateFlow(Unit)

    val accessTokenObservable: Flow<String> get() = authTokenFlow
    private val authTokenFlow = MutableStateFlow("")

    val isUserLogged: Boolean get() = accountManager.getAccountsByType(USER_ACCOUNT_TYPE).isNotEmpty()
    private val isUserLoggedFlow = MutableStateFlow(isUserLogged)

    val authToken get() = account?.run { accountManager.blockingGetAuthToken(this, AUTH_TOKEN_TYPE, false) }


    fun getObservableAccessTokenFromAccount(): Flow<String> =
        MutableStateFlow(account?.run { accountManager.blockingGetAuthToken(this, AUTH_TOKEN_TYPE, false) } ?: "")

    fun updateAccessAndPassword(accessToken: String, password: String) {
        account?.let {
            accountManager.setAuthToken(it, AUTH_TOKEN_TYPE, accessToken)
            accountManager.setPassword(it, password)
            this.authTokenFlow.value = accessToken
        }
    }

    fun isUserLoggedObservable(): Flow<Boolean> = isUserLoggedFlow

    fun getUserLoggedStatusChange(): Flow<Unit> = userLoggedStatusChanged

    fun addAccount(credentials: UserCredentials, authToken: String) {
        val account = Account(credentials.email, USER_ACCOUNT_TYPE)
        accountManager.getAccountsByType(USER_ACCOUNT_TYPE).forEach()
        {
            it.run {
                if (account.name == this.name) {
                    accountManager.setAuthToken(account, AUTH_TOKEN_TYPE, authToken)
                    accountManager.setPassword(account, credentials.password)
                    return
                } else {
                    removeAccount(it)
                }
            }
        }
        if (accountManager.addAccountExplicitly(account, credentials.password, null)) {
            accountManager.setAuthToken(account, AUTH_TOKEN_TYPE, authToken)
        }
        setLoggedStatus(true)
    }

    fun removeAccount() {
        setLoggedStatus(false)
        accountManager.getAccountsByType(USER_ACCOUNT_TYPE).forEach { removeAccount(it) }
        authTokenFlow.value = ""
    }

    fun setLoggedStatus(status: Boolean) {
        isUserLoggedFlow.value = status
        userLoggedStatusChanged.value = Unit
    }

    @Suppress("DEPRECATION")
    private fun removeAccount(account: Account) {
        accountManager.removeAccount(account, null, null)
    }
}