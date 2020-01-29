package com.tidaltestapp.data.model.artist


import com.google.gson.annotations.SerializedName

data class SearchArtistsApiResponse(
    @SerializedName("data")
    val `data`: List<Artist>?,
    @SerializedName("total")
    val total: Int?
)