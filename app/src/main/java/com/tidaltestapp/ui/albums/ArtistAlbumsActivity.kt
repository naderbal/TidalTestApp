package com.tidaltestapp.ui.albums

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.tidaltestapp.R
import com.tidaltestapp.data.model.artistAlbums.Album
import com.tidaltestapp.ui.albums.listing.AlbumsListingAdapter
import com.tidaltestapp.ui.base.BaseActivity
import com.tidaltestapp.ui.tracks.TracksOfAlbumActivity
import kotlinx.android.synthetic.main.activity_albums.*
import timber.log.Timber

class ArtistAlbumsActivity : BaseActivity(), AlbumsView {

    companion object {
        const val EXTRA_ARTIST_ID = "extra_artist_id"
        const val EXTRA_ARTIST_NAME = "extra_artist_name"
    }

    private lateinit var presenter: AlbumsPresenter
    private lateinit var adapter: AlbumsListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        configureToolbar(R.string.albums)
        // get passed artist id with intent
        val artistId = intent.getStringExtra(EXTRA_ARTIST_ID)
        val artistName = intent.getStringExtra(EXTRA_ARTIST_NAME)
        Timber.d(artistId)
        Timber.d(artistName)
        //init presenter
        initPresenter(artistId)
        // init listing
        initListing(artistName)
    }

    private fun initListing(artistName: String) {
        rv_albums.layoutManager = GridLayoutManager(this, 2)
        adapter = AlbumsListingAdapter(imageLoader, artistName, object: AlbumsListingAdapter.AlbumListingListener{
            override fun onAlbumSelected(album: Album) {
                openTracksOfAlbumSection(album, artistName)
            }
        })
        rv_albums.adapter = adapter
    }

    private fun initPresenter(artistId: String) {
        presenter = AlbumsPresenter(artistId, this, interactor)
        presenter.onStart()
    }

    private fun openTracksOfAlbumSection(album: Album, artistName: String) {
        // open tracks of album section after adding all fields to intent
        val intent = Intent(this, TracksOfAlbumActivity::class.java)
        intent.putExtra(TracksOfAlbumActivity.EXTRA_ALBUM_ID, album.id)
        intent.putExtra(TracksOfAlbumActivity.EXTRA_ALBUM_COVER, album.coverXl)
        intent.putExtra(TracksOfAlbumActivity.EXTRA_ALBUM_TITLE, album.title)
        intent.putExtra(TracksOfAlbumActivity.EXTRA_ARTIST_NAME, artistName)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showAlbums(albums: List<Album>) {
        Timber.d("albums data received")
        adapter.addItems(albums)
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
