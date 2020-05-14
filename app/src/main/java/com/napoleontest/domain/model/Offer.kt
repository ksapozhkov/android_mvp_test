package com.napoleontest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Offer(
    @field:PrimaryKey
    val id: String,
    val name: String,
    val desc: String,
    val image: String,
    val groupName: String,
    val type: String,
    val price: Float,
    val discount: Float
) {

    fun isProduct(): Boolean {
        return type == "product"
    }

    fun getDiscountPrice(): Int {
        return if (isProduct()) {
            (price * (1 - discount)).toInt()
        } else 0
    }

    fun discountInt(): Int {
        return if (isProduct()) {
            (discount * 100).toInt()
        } else 0
    }

}