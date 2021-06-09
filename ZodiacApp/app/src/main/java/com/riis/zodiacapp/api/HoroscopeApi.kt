package com.riis.zodiacapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApi {
    @GET("theastrologer/api/horoscope/{sign}/today/")
    fun fetchHoroscope(@Path("sign") sign: String): Call<String>
}