package com.tidaltestapp.ui.base

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.tidaltestapp.R
import com.tidaltestapp.data.interactor.Interactor
import com.tidaltestapp.di.DaggerDeps
import com.tidaltestapp.di.Deps
import com.tidaltestapp.di.network.NetworkModule
import com.tidaltestapp.utils.ImageLoader
import javax.inject.Inject

/**
 * Created by naderbaltaji on 2020-01-29
 */

open class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var interactor: Interactor
    lateinit var deps : Deps
    lateinit var imageLoader: ImageLoader
    private var mToolbar: Toolbar? = null
    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencyInjection()
        deps.inject(this)
        imageLoader = ImageLoader(this)
    }

    protected fun configureToolbar(resTitle: Int?) {
        setToolbarAsUp(R.drawable.ic_arrow_back_white_24dp) { v -> onBackPressed() }
        val toolbar = getToolbar()
        mHandler.post {
            if (resTitle != null) {
                toolbar.title = getString(resTitle)
                } else {
                toolbar.title = ""
            }
            toolbar.setTitleTextColor(
                ContextCompat.getColor(this,
                    R.color.colorAccent))
        }
    }

    fun getToolbar(): Toolbar {
        if (mToolbar == null) {
            mToolbar = findViewById(R.id.toolbar)
            if (mToolbar != null) {
                setSupportActionBar(mToolbar)
            }
        }
        return this.mToolbar!!
    }

    private fun setToolbarAsUp(@DrawableRes imageDrawable: Int, clickListener: (Any) -> Unit) {
        // Initialise the toolbar
        getToolbar()

        mToolbar?.setNavigationIcon(imageDrawable)
        mToolbar?.navigationContentDescription = ""
        mToolbar?.setNavigationOnClickListener(clickListener)
    }

    private fun initDependencyInjection() {
        deps = DaggerDeps.builder()
            .networkModule(NetworkModule())
            .build()
    }

    fun showToast(resId: Int) {
        Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}