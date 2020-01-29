package com.tidaltestapp.ui.tracks.listing

import com.tidaltestapp.data.model.albumTracks.Track

/**
 * Created by naderbaltaji on 2020-01-29
 */
data class TrackListingItem(val track: Track, val trackNumber: Int) : TrackOfAlbumListingItem(TrackOfAlbumListingType.TRACK)