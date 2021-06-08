package com.riis.zodiacapp.api

import retrofit2.Call
import retrofit2.http.GET

interface HoroscopeApi {
    @GET("theastrologer/api/horoscope/aries/today/")
    fun fetchHoroscope(): Call<String>
}