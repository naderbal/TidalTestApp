package com.findre.findre.ui.base

import com.tidaltestapp.data.interactor.Interactor
import com.tidaltestapp.ui.base.BaseView
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by naderbaltaji on 2020-01-29
 */

abstract class BasePresenter<V : BaseView>(open var view: V?, open val interactorService: Interactor) {
    val disposable = CompositeDisposable()

    abstract fun onStart()

    open fun onDestroy() {
        disposable.clear()
        view = null
    }

    fun <T> applySchedulers(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}