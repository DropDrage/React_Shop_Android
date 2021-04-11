package com.wholedetail.react_shop_android.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.wholedetail.react_shop_android.R

class AuthActivity : AppCompatActivity(R.layout.activity_base) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Navigation.findNavController(this, R.id.nav_host_fragment).setGraph(R.navigation.auth_navigation)
    }
}