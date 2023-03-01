package com.fitpeo.imagedemo.network

import com.fitpeo.imagedemo.utils.performGetOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiDataSource: ApiDataSource,
) {

    fun getPhoto() = performGetOperation {
        apiDataSource.getPhoto()
    }

}