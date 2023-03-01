package com.fitpeo.imagedemo

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fitpeo.imagedemo.base.BaseViewModel
import com.fitpeo.imagedemo.base.ViewInteractor
import com.fitpeo.imagedemo.network.ApiDataSource
import com.fitpeo.imagedemo.network.ApiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock

@ExperimentalCoroutinesApi
abstract class ViewModelTest<viewInterc : ViewInteractor, VM : BaseViewModel<viewInterc>> {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var apiDataSource: ApiDataSource


    protected lateinit var mViewModel: VM

    protected abstract fun createViewModel(): VM
    protected abstract fun getViewClass(): viewInterc
    lateinit var view: viewInterc

    @Before
    @CallSuper
    open fun setup() {
        mViewModel = createViewModel()
        mViewModel.viewInteractor = getViewClass()
        view = mViewModel.viewInteractor!!

    }

}