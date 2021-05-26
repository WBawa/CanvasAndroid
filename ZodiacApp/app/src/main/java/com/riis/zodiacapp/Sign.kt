package com.riis.zodiacapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Sign(
    @PrimaryKey val id: Int = 1,
    var name: String = "",
    var description: String = "",
    var symbol: String = "",
)