package com.riis.photogallery.api

import com.riis.photogallery.FlickrResponse
import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {

    @GET("services/rest/?method=flickr.interestingness.getList" +"&api_key=91cccc76b1e3373c3f177f32003a06b4" +"&format=json" +"&nojsoncallback=1" +"&extras=url_s")
//    @GET("services/rest/?method=flickr.interestingness.getList&api_key=91cccc76b1e3373c3f177f32003a06b4&format=json&nojsoncallback=1&extras=url_s")
//    @GET("/")

    fun fetchPhotos(): Call<FlickrResponse>
}