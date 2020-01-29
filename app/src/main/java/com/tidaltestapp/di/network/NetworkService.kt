package com.tidaltestapp.di.network

import com.tidaltestapp.data.model.albumTracks.TracksOfAlbumApiResponse
import com.tidaltestapp.data.model.artist.SearchArtistsApiResponse
import com.tidaltestapp.data.model.artistAlbums.ArtistAlbumsApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by naderbaltaji on 2020-01-29
 */
interface NetworkService {
    @GET("search/artist")
    fun searchArtists(@Query("q") artistQuery: String) : Single<SearchArtistsApiResponse>

    @GET("artist/{id}/albums")
    fun fetchArtistAlbums(@Path("id") artistId: String) : Single<ArtistAlbumsApiResponse>

    @GET("album/{id}/tracks")
    fun fetchTracksOfAlbum(@Path("id") albumId: String) : Single<TracksOfAlbumApiResponse>
}