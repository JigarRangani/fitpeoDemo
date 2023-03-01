package com.fitpeo.imagedemo.network

import com.fitpeo.imagedemo.ui.login.ImageDataResponse
import retrofit2.Response
import retrofit2.http.GET


interface ApiServices {

    @GET(ApiEndPoint.PHOTOS)
    suspend fun getPhoto():Response<ImageDataResponse>

}