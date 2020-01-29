package com.tidaltestapp.ui.albums

import com.tidaltestapp.data.model.artistAlbums.Album
import com.tidaltestapp.ui.base.BaseView

/**
 * Created by naderbaltaji on 2020-01-29
 */
interface AlbumsView : BaseView {
    fun showAlbums(albums: List<Album>)
}