package com.tidaltestapp.ui.tracks

import com.tidaltestapp.ui.base.BaseView
import com.tidaltestapp.ui.tracks.listing.TrackOfAlbumListingItem

/**
 * Created by naderbaltaji on 2020-01-29
 */
interface TracksOfAlbumView : BaseView{
    fun showTracks(items: List<TrackOfAlbumListingItem>)
}