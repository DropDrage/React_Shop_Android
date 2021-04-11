package com.wholedetail.react_shop_android.model

data class User(val id: Long, val role: Role, val username: String, val bio: String, val isBlocked: Boolean)

enum class Role {
    ROLE_USER, ROLE_ADMIN
}
