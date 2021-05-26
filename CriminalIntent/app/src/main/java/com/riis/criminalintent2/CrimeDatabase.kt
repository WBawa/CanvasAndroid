package com.riis.criminalintent2

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Crime::class], version=1)
@TypeConverters(CrimeTypeConverters::class)

abstract class CrimeDatabase : RoomDatabase() {
    abstract fun crimeDao(): CrimeDao
}