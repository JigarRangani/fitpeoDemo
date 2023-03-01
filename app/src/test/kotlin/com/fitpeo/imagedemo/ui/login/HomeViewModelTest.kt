package com.fitpeo.imagedemo.ui.login

import androidx.lifecycle.MutableLiveData
import com.fitpeo.imagedemo.ViewModelTest
import com.fitpeo.imagedemo.getOrAwaitValueTest
import com.fitpeo.imagedemo.network.ApiRepository
import com.fitpeo.imagedemo.util.Helper
import com.fitpeo.imagedemo.utils.Resource
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class HomeViewModelTest : ViewModelTest<HomeContracts, HomeViewModel>() {

    @Mock
    lateinit var mockView: HomeContracts

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        apiRepository = ApiRepository(apiDataSource)
    }

    @Test
    fun `check init things`() {
        mViewModel.initThings()
        Mockito.verify(view).setUpObserver()

    }

    @Test
    fun `home data api success`() {
        val livedata = MutableLiveData<Resource<ImageDataResponse>>().apply {
            value = Resource.success(getHomeDataResponse())
        }

        val repo = Mockito.spy(ApiRepository(apiDataSource))
        mViewModel.apiRepository = repo
        Mockito.`when`(repo.getPhoto()).thenReturn(livedata)
        mViewModel.getPhoto()
        val value = mViewModel.getPhoto().getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Resource.Status.SUCCESS)
    }

    private fun getHomeDataResponse(): ImageDataResponse {
        return Gson().fromJson(
            Helper.readFromFile("homeresponse.json"),
            ImageDataResponse::class.java
        )
    }

    @Test
    fun `home data api loading`() {
        val livedata = MutableLiveData<Resource<ImageDataResponse>>().apply {
            value = Resource.loading(getHomeDataResponse())
        }

        val repo = Mockito.spy(ApiRepository(apiDataSource))
        mViewModel.apiRepository = repo
        Mockito.`when`(repo.getPhoto()).thenReturn(livedata)
        mViewModel.getPhoto()
        val value = mViewModel.getPhoto().getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Resource.Status.LOADING)
    }


    override fun createViewModel(): HomeViewModel {
        return HomeViewModel(apiRepository)
    }

    override fun getViewClass(): HomeContracts {
        return mockView
    }

}