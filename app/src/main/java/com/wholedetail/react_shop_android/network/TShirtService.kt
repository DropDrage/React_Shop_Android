package com.wholedetail.react_shop_android.network

import com.wholedetail.react_shop_android.model.TShirt
import com.wholedetail.react_shop_android.model.TShirtMinified
import com.wholedetail.react_shop_android.model.TShirtSize
import com.wholedetail.react_shop_android.model.Tag
import com.wholedetail.react_shop_android.model.Topic
import com.wholedetail.react_shop_android.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TShirtService {
    @JvmSuppressWildcards
    @GET("all")
    suspend fun getAll(
        @Query("authors") authors: List<User>? = null,
        @Query("topic") topic: Topic? = null,
        @Query("tag") tag: Tag? = null,
        @Query("sizes") sizes: List<TShirtSize>? = null,
        @Query("priceMin") priceMin: Float? = null,
        @Query("priceMax") priceMax: Float? = null,
    ): List<TShirtMinified>

    @GET("/get/{tShirtId}")
    suspend fun getTShirt(@Path("tShirtId") tShirtId: Long): TShirt
}