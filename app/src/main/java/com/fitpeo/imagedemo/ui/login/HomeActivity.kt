package com.fitpeo.imagedemo.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.fitpeo.imagedemo.base.BaseActivity
import com.valora.tradecall.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeContracts {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

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

    }

    @Override
    override fun setUpView() {

    }

    @Override
    override fun finishScreen() {
        finish()
    }

    @Override
    override fun setData() {

    }


}