package com.fitpeo.imagedemo.ui.login

import com.fitpeo.imagedemo.base.ViewInteractor

interface HomeContracts : ViewInteractor {
    fun setUpObserver()
    fun finishScreen()
    fun setData()
    fun setUpView()
}