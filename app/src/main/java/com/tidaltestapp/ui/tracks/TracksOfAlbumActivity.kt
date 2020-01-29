package com.tidaltestapp.ui.tracks

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tidaltestapp.R
import com.tidaltestapp.ui.base.BaseActivity
import com.tidaltestapp.ui.tracks.listing.TrackOfAlbumListingItem
import com.tidaltestapp.ui.tracks.listing.TracksOfAlbumListingAdapter
import kotlinx.android.synthetic.main.activity_tracks_of_album.*

class TracksOfAlbumActivity : BaseActivity(), TracksOfAlbumView {

    companion object {
        const val EXTRA_ALBUM_ID = "extra_album_id"
        const val EXTRA_ALBUM_TITLE = "extra_album_title"
        const val EXTRA_ALBUM_COVER = "extra_album_cover"
        const val EXTRA_ARTIST_NAME = "extra_artist_name"
    }

    lateinit var presenter: TracksOfAlbumPresenter
    lateinit var adapter: TracksOfAlbumListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracks_of_album)
        configureToolbar(null)
        // get fields passed with intent
        val albumId = intent.getStringExtra(EXTRA_ALBUM_ID)
        val albumTitle = intent.getStringExtra(EXTRA_ALBUM_TITLE)
        val albumCover = intent.getStringExtra(EXTRA_ALBUM_COVER)
        val artistName = intent.getStringExtra(EXTRA_ARTIST_NAME)
        // presenter
        initPresenter(albumId)
        // bind views
        bindViews(albumTitle, albumCover, artistName)
        // listing
        initListing()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun bindViews(albumTitle: String?, albumCover: String?, artistName: String?) {
        // album title
        albumTitle?.let { tv_album_title.text = albumTitle }
        // cover
        imageLoader.loadImage(albumCover, iv_cover)
        // artist name
        artistName?.let { tv_artist_name.text = artistName }
    }

    private fun initPresenter(albumId: String) {
        presenter = TracksOfAlbumPresenter(albumId,this, interactor)
        presenter.onStart()
    }

    private fun initListing() {
        rv_tracks.layoutManager = LinearLayoutManager(this)
        adapter = TracksOfAlbumListingAdapter()
        rv_tracks.adapter = adapter
    }

    override fun showTracks(items: List<TrackOfAlbumListingItem>) {
        adapter.addListingItems(items)
    }

    override fun showLoader() {
        view_progress.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        view_progress.visibility = View.GONE
    }

    override fun showError(message: String?) {
        showToast(message ?: getString(R.string.default_error))
    }
}
