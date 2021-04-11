package com.wholedetail.react_shop_android.model

import java.time.LocalDate

data class TShirt(
    val id: Long,
    val title: String,
    val description: String,
    val price: Float,
    val topic: Topic,
    val createDate: LocalDate,
    val updateDate: LocalDate,
    val author: User,
) {
    lateinit var imagesUrl: Array<String>
    lateinit var tags: Array<Tag>
    lateinit var sizes: Array<TShirtSize>
    lateinit var colors: Array<String>

    constructor(
        id: Long,
        title: String,
        description: String,
        price: Float,
        topic: Topic,
        createDate: LocalDate,
        updateDate: LocalDate,
        author: User,
        imagesUrl: Array<String>,
        tags: Array<Tag>,
        sizes: Array<TShirtSize>,
        colors: Array<String>
    ) : this(id, title, description, price, topic, createDate, updateDate, author) {
        this.imagesUrl = imagesUrl
        this.tags = tags
        this.sizes = sizes
        this.colors = colors
    }
}

enum class TShirtSize {
    XXS, XS, S, M, L, XL, XXL, XXXL
}
