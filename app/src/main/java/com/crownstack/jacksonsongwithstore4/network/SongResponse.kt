package com.crownstack.jacksonsongwithstore4.network

import com.crownstack.jacksonsongwithstore4.model.SongInfo
import com.google.gson.annotations.SerializedName

data class SongResponse(
    @SerializedName("results")
    val results: List<SongInfo>
)

