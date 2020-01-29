package com.tidaltestapp.ui.tracks

import com.findre.findre.ui.base.BasePresenter
import com.tidaltestapp.data.interactor.Interactor
import com.tidaltestapp.data.model.albumTracks.Track
import com.tidaltestapp.ui.tracks.listing.TrackListingItem
import com.tidaltestapp.ui.tracks.listing.TrackOfAlbumListingItem
import com.tidaltestapp.ui.tracks.listing.VolumeListingItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by naderbaltaji on 2020-01-29
 */
class TracksOfAlbumPresenter(val albumId: String, view: TracksOfAlbumView, val interactor: Interactor) : BasePresenter<TracksOfAlbumView>(view, interactor){
    override fun onStart() {
        fetchTracksOfAlbum()
    }

    private fun fetchTracksOfAlbum() {
        view?.showLoader()
        disposable.add(interactor.fetchTracksOfAlbum(albumId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               view?.hideLoader()
                if (!it.data.isNullOrEmpty()){
                    val items = generateTracksOfAlbumListingItems(it.data)
                    view?.showTracks(items)
                }
            }, {
                view?.hideLoader()
                view?.showError(it.message)
            }))
    }

    private fun generateTracksOfAlbumListingItems(tracks: List<Track>) : List<TrackOfAlbumListingItem> {
        val items = ArrayList<TrackOfAlbumListingItem>()
        // sort tracks by disk number to group tracks by volume under each other
        val sortedTracks = tracks.sortedWith(compareBy { it.diskNumber })
        // save disk number which will be used in loop to compare current track's disk number with
        // when past number does not equal current track's disk number create a new volume
        var pastDiskNumber = -1 // start with -1 to create first volume initially
        for ((index, track) in sortedTracks.withIndex()) {
            val currentDiskNumber = track.diskNumber
            if (pastDiskNumber != currentDiskNumber) {
                items.add(VolumeListingItem("Volume $currentDiskNumber"))
                pastDiskNumber = currentDiskNumber
            }
            // add track to listing items
            items.add(TrackListingItem(track, (index+1)))
        }
        return items
    }
}