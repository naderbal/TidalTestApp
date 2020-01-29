package com.tidaltestapp.ui.searchArtists

import com.tidaltestapp.data.model.artist.Artist
import com.tidaltestapp.ui.base.BaseView

/**
 * Created by naderbaltaji on 2020-01-29
 */
interface SearchArtistsView : BaseView {
    fun showArtists(data: List<Artist>)

    fun clearList()
}