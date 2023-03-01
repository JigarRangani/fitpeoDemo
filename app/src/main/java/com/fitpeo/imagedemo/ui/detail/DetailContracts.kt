package com.fitpeo.imagedemo.ui.detail

import com.fitpeo.imagedemo.base.ViewInteractor

interface DetailContracts : ViewInteractor {
    fun setUpObserver()
    fun finishScreen()
    fun setData()
    fun setUpView()
}