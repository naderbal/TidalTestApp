package com.tidaltestapp.data.model.albumTracks


import com.google.gson.annotations.SerializedName

data class TracksOfAlbumApiResponse(
    @SerializedName("data")
    val `data`: List<Track>?,
    @SerializedName("total")
    val total: Int?
)