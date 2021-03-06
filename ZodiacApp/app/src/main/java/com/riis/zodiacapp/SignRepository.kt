package com.riis.zodiacapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room

private const val DATABASE_NAME = "zodiac_db"
private const val DATABASE_DIR = "databases/zodiac_db"

class SignRepository private constructor(context: Context) {
    private val database: SignDatabase = Room.databaseBuilder(
        context.applicationContext,
        SignDatabase::class.java,
        DATABASE_NAME
    ).createFromAsset(DATABASE_DIR).build()

    private val signDao = database.signDao()

    fun getSigns(): LiveData<List<Sign>> = signDao.getSigns()

    fun getSign(id: Int): LiveData<Sign?> = signDao.getSign(id)

    companion object {
        private var INSTANCE: SignRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = SignRepository(context)
            }
        }

        fun get(): SignRepository {
            return INSTANCE ?: throw IllegalStateException("Not Initialized")
        }
    }
}
