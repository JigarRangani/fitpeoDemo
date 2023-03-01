package com.fitpeo.imagedemo.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.fitpeo.imagedemo.base.BaseViewModel
import com.fitpeo.imagedemo.network.ApiRepository
import com.fitpeo.imagedemo.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject
constructor(
    private val apiRepository: ApiRepository,
) : BaseViewModel<HomeContracts>() {


    fun initThings() {
        viewInteractor?.setUpObserver()
        viewInteractor?.setUpView()
    }

    fun onBackClick() {
        viewInteractor?.finishScreen()
    }

}