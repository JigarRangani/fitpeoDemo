package com.fitpeo.imagedemo.ui.detail

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.fitpeo.imagedemo.base.BaseActivity
import com.fitpeo.imagedemo.databinding.ActivityDetailBinding
import com.fitpeo.imagedemo.ui.login.ImageDataResponseItem
import com.fitpeo.imagedemo.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailVM>(), DetailContracts {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, DetailActivity::class.java)
        }
    }

    var imageDataResponseItem:ImageDataResponseItem? = null

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.viewInteractor = this
        imageDataResponseItem = intent.getSerializableExtra(Constants.DATA) as ImageDataResponseItem?
        viewModel.initThings()
    }

    override val viewModel: DetailVM by viewModels()

    @Override
    override fun setBinding(layoutInflater: LayoutInflater): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    @Override
    override fun setUpObserver() {

    }

    @Override
    override fun setUpView() {
        imageDataResponseItem?.let {
            data ->
            binding.tvDetail.text = data.title
            Picasso.get()
                .load(data.url)
                .into(binding.imgDemo)
        }
    }

    @Override
    override fun finishScreen() {
        finish()
    }

    @Override
    override fun setData() {

    }


}