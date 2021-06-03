package com.riis.criminalintent2

import androidx.room.TypeConverter
import java.time.LocalTime
import java.util.*


class CrimeTypeConverters {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let { Date(it) }
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun fromGregorianCalendar(time: GregorianCalendar?): String? {
        return time.toString()
    }
}