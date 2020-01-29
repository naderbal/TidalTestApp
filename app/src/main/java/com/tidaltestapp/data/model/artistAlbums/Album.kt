package com.tidaltestapp.data.model.artistAlbums


import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("cover")
    val cover: String?,
    @SerializedName("cover_big")
    val coverBig: String?,
    @SerializedName("cover_medium")
    val coverMedium: String?,
    @SerializedName("cover_small")
    val coverSmall: String?,
    @SerializedName("cover_xl")
    val coverXl: String?,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean?,
    @SerializedName("fans")
    val fans: Int?,
    @SerializedName("genre_id")
    val genreId: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("record_type")
    val recordType: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("tracklist")
    val tracklist: String?,
    @SerializedName("type")
    val type: String?
)