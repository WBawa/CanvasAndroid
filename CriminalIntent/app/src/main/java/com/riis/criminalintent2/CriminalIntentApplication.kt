package com.riis.criminalintent2

import android.app.Application
import com.riis.criminalintent2.CrimeRepository

class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}