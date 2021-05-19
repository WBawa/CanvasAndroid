package com.riis.zodiacapp

import androidx.lifecycle.ViewModel

class ZodiacListViewModel : ViewModel() {
    init{
        val zodiac = listOf("Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces")
    }
}