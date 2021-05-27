package com.riis.zodiacapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sign(
    @PrimaryKey val id: Int,
    var name: String = "",
    var description: String = "",
    var symbol: String = "",
    var month: String = ""
)