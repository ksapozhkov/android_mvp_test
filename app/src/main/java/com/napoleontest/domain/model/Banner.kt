package com.napoleontest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Banner(
    @field:PrimaryKey
    val id: String,
    val title: String?,
    val desc: String?,
    val image: String
)
