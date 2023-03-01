package com.fitpeo.imagedemo.base

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.fitpeo.imagedemo.utils.ConnectionLiveData
import com.google.android.material.snackbar.Snackbar
import java.util.*

abstract class BaseActivity<B : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    protected lateinit var binding: B

//    protected val viewModel: VM by lazy { ViewModelProvider(this).get(getViewModelClass()) }
    protected abstract val viewModel: VM

    var restart = false

  /*  abstract fun sharePref() :SharedPreferenceManager*/

    lateinit var commonDialog : Dialog

    lateinit var connectionLiveData: ConnectionLiveData

    abstract fun setBinding(layoutInflater: LayoutInflater): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setBinding(layoutInflater)
        setContentView(binding.root)
        commonDialog = showNoConnectionDialog(this)
        connectionLiveData = ConnectionLiveData(this)
        netWorkCheck()

    }

    fun showLoader(){

    }

    fun hideLoader(){

    }

    fun netWorkCheck() {
        connectionLiveData.observe(this,{ isConnected ->
            isConnected?.let {
                showMessage(isConnected)
            }
        })
    }

    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {
            if (!commonDialog.isShowing){
                commonDialog.show()
            }
        } else {
            if (commonDialog.isShowing){
                commonDialog.dismiss()
            }
        }
    }
  /*  override fun attachBaseContext(newBase: Context) {// get chosen language from shread preference
        val localeToSwitchTo = sharePref().language.take(2)
        Log.e("BaseActivity", "laungauge $localeToSwitchTo")

        val localeUpdatedContext: ContextWrapper = ContextUtils.updateLocale(
            newBase, updateResources(
                newBase,
                localeToSwitchTo
            )
        )
        super.attachBaseContext(localeUpdatedContext)
    }*/
    private fun updateResources(context: Context, language: String) : Locale {
        try {
            Configuration(context.resources.configuration).run {
                Locale.setDefault(Locale(language).also { locale ->
                    return locale
                })
            }
        }catch (e: Exception){
            Log.e("BaseActivity", e.printStackTrace().toString())
            return Locale.US
        }
    }


    override fun onResume() {
        super.onResume()
        if (restart){
            startActivity(intent)
            overridePendingTransition(0, 0)
            finish()
        }
    }
    fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    fun showNoConnectionDialog(context: Context): AlertDialog {
        return AlertDialog.Builder(context).setMessage(
            "no internet"
        ).setCancelable(false).setNeutralButton("check connection")
        { _, _ ->
            try {
                val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }.create()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (commonDialog.isShowing){
            commonDialog.dismiss()
        }
    }
}