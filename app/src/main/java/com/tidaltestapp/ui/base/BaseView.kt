package com.tidaltestapp.ui.base

/**
 * Created by naderbaltaji on 2020-01-29
 */

interface BaseView {
    fun showLoader()

    fun hideLoader()

    fun showError(message: String?)
}