package com.fitpeo.imagedemo.network

import android.content.Context
import com.fitpeo.imagedemo.utils.SharedPreferenceManager
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val apiServices: ApiServices,
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val context: Context
) : BaseDataSource(context) {

    suspend fun getPhoto() = getResult {
        apiServices.getPhoto()
    }


}