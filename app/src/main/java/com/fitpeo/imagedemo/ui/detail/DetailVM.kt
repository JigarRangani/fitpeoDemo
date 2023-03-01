package com.fitpeo.imagedemo.ui.detail

import androidx.lifecycle.LiveData
import com.fitpeo.imagedemo.base.BaseViewModel
import com.fitpeo.imagedemo.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject
constructor(
    private val apiRepository: ApiRepository,
) : BaseViewModel<DetailContracts>() {

    fun initThings() {
        viewInteractor?.setUpObserver()
        viewInteractor?.setUpView()
    }

    fun onBackClick() {
        viewInteractor?.finishScreen()
    }

}