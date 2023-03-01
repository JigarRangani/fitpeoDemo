package com.fitpeo.imagedemo.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.fitpeo.imagedemo.base.BaseActivity
import com.fitpeo.imagedemo.databinding.ActivityHomeBinding
import com.fitpeo.imagedemo.ui.detail.DetailActivity
import com.fitpeo.imagedemo.utils.Constants
import com.fitpeo.imagedemo.utils.Resource
import com.fitpeo.imagedemo.utils.makeGone
import com.fitpeo.imagedemo.utils.makeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeContracts {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    val adapter = HomeAdapter()

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewInteractor = this
        viewModel.initThings()
    }

    override val viewModel: HomeViewModel by viewModels()

    @Override
    override fun setBinding(layoutInflater: LayoutInflater): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    @Override
    override fun setUpObserver() {
        Log.e("HomeActivity","calling appi")
        viewModel.getPhoto().observe(this, Observer {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            hideLoading()
                            it.data?.let {
                                imageDataResponse ->
                                adapter.updateItems(imageDataResponse)
                            }
                        }

                        Resource.Status.ERROR -> {
                            hideLoading()
                        }

                        Resource.Status.LOADING -> {
                            showLoading()
                        }
                    }
                })
    }

    private fun showLoading() {
        binding.progressBar.makeVisible()
    }

    private fun hideLoading() {
        binding.progressBar.makeGone()
    }

    @Override
    override fun setUpView() {
        binding.rvHome.adapter = adapter
        adapter.homeContracts = this
    }

    override fun onImageItemClick(imageDataResponseItem: ImageDataResponseItem) {
        startActivity(DetailActivity.getIntent(this).putExtra(Constants.DATA,imageDataResponseItem))
    }

    @Override
    override fun finishScreen() {
        finish()
    }

    @Override
    override fun setData() {

    }


}