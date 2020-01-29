package com.tidaltestapp.ui.albums

import com.findre.findre.ui.base.BasePresenter
import com.tidaltestapp.data.interactor.Interactor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by naderbaltaji on 2020-01-29
 */
class AlbumsPresenter(private val artistId: String, albumsView: AlbumsView, val interactor: Interactor) : BasePresenter<AlbumsView>(albumsView, interactor) {
    override fun onStart() {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        view?.showLoader()
        disposable.add(
            interactor.fetchArtistAlbums(artistId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.hideLoader()
                    it?.data?.let { albums ->
                        view?.showAlbums(albums)
                    }
                }, {
                    view?.hideLoader()
                    view?.showError(it.message)
                })
        )
    }
}