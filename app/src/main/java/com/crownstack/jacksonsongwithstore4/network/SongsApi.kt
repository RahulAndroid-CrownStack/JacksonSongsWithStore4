package com.crownstack.jacksonsongwithstore4.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SongsApi {

    @GET("search")
    suspend fun getSongList(@Query("term") term: String): SongResponse
}