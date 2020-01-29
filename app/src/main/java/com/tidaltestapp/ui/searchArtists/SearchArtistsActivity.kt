package com.tidaltestapp.ui.searchArtists

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.appcompat.queryTextChangeEvents
import com.tidaltestapp.R
import com.tidaltestapp.data.model.artist.Artist
import com.tidaltestapp.ui.albums.ArtistAlbumsActivity
import com.tidaltestapp.ui.base.BaseActivity
import com.tidaltestapp.ui.searchArtists.listing.SearchArtistsListingAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search_artists.*
import timber.log.Timber
import java.util.concurrent.TimeUnit


class SearchArtistsActivity : BaseActivity(), SearchArtistsView {

    lateinit var presenter: SearchArtistsPresenter
    lateinit var adapter: SearchArtistsListingAdapter
    private var queryDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_artists)
        // presenter
        initPresenter()
        // listing
        initListing()
        // search queries
        configureArtistSearching()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        queryDisposable?.dispose()
    }

    private fun initPresenter() {
        presenter = SearchArtistsPresenter(interactor, this)
        presenter.onStart()
    }

    private fun initListing() {
        rv_artists.layoutManager = LinearLayoutManager(this)
        adapter = SearchArtistsListingAdapter(imageLoader, object : SearchArtistsListingAdapter.SearchArtistListListener{
            override fun onArtistSelected(artist: Artist) {
                openArtistAlbumsSection(artist)
            }
        })
        rv_artists.adapter = adapter
    }

    private fun openArtistAlbumsSection(artist: Artist) {
        val intent = Intent(this, ArtistAlbumsActivity::class.java)
        intent.putExtra(ArtistAlbumsActivity.EXTRA_ARTIST_ID, artist.id)
        intent.putExtra(ArtistAlbumsActivity.EXTRA_ARTIST_NAME, artist.name)
        startActivity(intent)
    }

    private fun configureArtistSearching() {
        search_view.setOnClickListener { search_view.isIconified = false }
        queryDisposable = search_view.queryTextChangeEvents()
            .debounce(300, TimeUnit.MILLISECONDS) // emit events after 300 ms delay
            .skip(1) // skip initial empty event
            .switchMap { // switch mapping clears the result of previous event
                Observable.just(presenter.searchArtists(it.queryText.toString())).subscribeOn(Schedulers.io())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                showLoader()
            }
            .subscribe ({
                // do nothing, result handled by the presenter
            }, {
                Timber.e("error")
            })
    }

    override fun showArtists(data: List<Artist>) {
        // show artists title
        tv_artists.visibility = View.VISIBLE
        // fill listing adapter with artists
        adapter.replaceItems(data)
    }

    override fun clearList() {
        // hide artists title
        tv_artists.visibility = View.GONE
        // clear list
        adapter.clearItems()
    }

    override fun showLoader() {
        view_progress.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        view_progress.visibility = View.GONE
    }

    override fun showError(message: String?) {
        showToast(getString(R.string.default_error))
    }
}
