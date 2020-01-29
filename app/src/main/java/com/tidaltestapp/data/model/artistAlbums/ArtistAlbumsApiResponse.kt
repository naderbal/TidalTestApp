package com.tidaltestapp.data.model.artistAlbums


import com.google.gson.annotations.SerializedName

data class ArtistAlbumsApiResponse(
    @SerializedName("data")
    val `data`: List<Album>?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("total")
    val total: Int?
)