package com.napoleontest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Offer(
    @field:PrimaryKey
    val id: String,
    val title: String,
    val desc: String,
    val image: String,
    val groupName: String,
    val type: String,
    val price: Float,
    val discount: Float
)