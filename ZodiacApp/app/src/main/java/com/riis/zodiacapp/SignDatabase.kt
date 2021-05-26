package com.riis.zodiacapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Sign::class], version=1)

abstract class SignDatabase : RoomDatabase() {
    abstract fun signDao(): SignDao
}