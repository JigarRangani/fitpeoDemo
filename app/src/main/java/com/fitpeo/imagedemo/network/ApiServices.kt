package com.fitpeo.imagedemo.network

import com.fitpeo.imagedemo.ui.otp.OtpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiServices {

    @POST(ApiEndPoint.LOGIN)
    suspend fun sendLogin(@Body loginRequest: LoginRequest): Response<ImageResponse>

  @POST(ApiEndPoint.VERIFY)
    suspend fun sendOtp(@Body loginRequest: OtpRequest): Response<ImageResponse>


}