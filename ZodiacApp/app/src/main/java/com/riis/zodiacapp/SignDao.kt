package com.riis.zodiacapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface SignDao {
    @Query("SELECT * FROM sign")
    fun getSigns(): LiveData<List<Sign>>

    @Query("SELECT * FROM sign WHERE id=(:id)")
    fun getSign(id: Int): LiveData<Sign?>
}