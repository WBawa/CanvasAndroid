package com.riis.zodiacapp

import android.app.Application

class ZodiacApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SignRepository.initialize(this)
    }
}