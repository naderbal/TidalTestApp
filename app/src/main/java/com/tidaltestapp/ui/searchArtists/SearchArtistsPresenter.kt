package com.tidaltestapp.ui.searchArtists

import com.findre.findre.ui.base.BasePresenter
import com.tidaltestapp.data.interactor.Interactor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by naderbaltaji on 2020-01-29
 */
class SearchArtistsPresenter(val interactor: Interactor, view: SearchArtistsView) : BasePresenter<SearchArtistsView>(view, interactor) {
    override fun onStart() {
    }

    fun searchArtists(query: String) {
        disposable.add(
            interactor.searchArtists(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.hideLoader()
                    if (!it.data.isNullOrEmpty()) {
                        view?.showArtists(it.data)
                    } else {
                        view?.clearList()
                    }
                }, {
                    view?.hideLoader()
                    view?.showError(it.message)
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

}