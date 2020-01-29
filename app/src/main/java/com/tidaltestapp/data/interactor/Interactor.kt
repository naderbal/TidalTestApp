package com.tidaltestapp.data.interactor

import com.tidaltestapp.data.model.albumTracks.TracksOfAlbumApiResponse
import com.tidaltestapp.data.model.artist.SearchArtistsApiResponse
import com.tidaltestapp.data.model.artistAlbums.ArtistAlbumsApiResponse
import com.tidaltestapp.di.network.NetworkService
import io.reactivex.Single

/**
 * Created by naderbaltaji on 2020-01-29
 */
class Interactor(private val networkService: NetworkService) {
    fun searchArtists(query: String) : Single<SearchArtistsApiResponse> {
        return networkService.searchArtists(query)
    }

    fun fetchArtistAlbums(artistId: String) : Single<ArtistAlbumsApiResponse> {
        return networkService.fetchArtistAlbums(artistId)
    }

    fun fetchTracksOfAlbum(albumId: String) : Single<TracksOfAlbumApiResponse> {
        return networkService.fetchTracksOfAlbum(albumId)
    }
}