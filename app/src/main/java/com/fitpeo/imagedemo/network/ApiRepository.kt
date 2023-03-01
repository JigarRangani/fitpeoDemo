package com.fitpeo.imagedemo.network

import com.fitpeo.imagedemo.ui.otp.OtpRequest
import com.fitpeo.imagedemo.utils.performGetOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiDataSource: ApiDataSource,
) {
    fun sendLogin(loginRequest: LoginRequest) = performGetOperation {
        apiDataSource.sendLogin(loginRequest)
    }

    fun sendOtp(otpRequest: OtpRequest) = performGetOperation {
        apiDataSource.sendOtp(otpRequest)
    }
}