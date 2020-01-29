package com.tidaltestapp.data.model.albumTracks


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("tracklist")
    val tracklist: String?,
    @SerializedName("type")
    val type: String?
)