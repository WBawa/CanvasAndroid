package com.riis.criminalintent2

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"
private const val DATABASE_PATH = "databases/crime-database"

class CrimeRepository private constructor(context: Context) {
    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).createFromAsset(DATABASE_PATH).build()

    private val crimeDao = database.crimeDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)

    fun updateCrime(crime: Crime) {
        executor.execute {
            crimeDao.updateCrime(crime)
        }
    }

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("Not Initialized")
        }
    }
}