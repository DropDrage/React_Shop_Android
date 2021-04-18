package com.wholedetail.react_shop_android.utils.retrofitconverters

import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class EnumConverterFactory : Converter.Factory() {
    override fun stringConverter(
        type: Type?, annotations: Array<out Annotation>?,
        retrofit: Retrofit?
    ): Converter<*, String>? =
        if (type is Class<*> && type.isEnum) Converter<Any?, String> { value -> (value as Enum<*>).name }
        else null
}