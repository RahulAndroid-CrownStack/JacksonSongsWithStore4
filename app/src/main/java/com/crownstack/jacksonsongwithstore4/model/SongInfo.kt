package com.crownstack.jacksonsongwithstore4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "song_info")
data class SongInfo(

    @ColumnInfo(name = "wrapperType")
    val wrapperType: String,

    @ColumnInfo(name = "artistId")
    val artistId: Int,


    @ColumnInfo(name = "collectionId")
    val collectionId: Int,

    @ColumnInfo(name = "artistName")
    val artistName: String,

    @PrimaryKey
    @ColumnInfo(name = "trackId")
    val trackId: Int,

    @ColumnInfo(name = "artworkUrl100")
    val artworkUrl100: String = "",

    @ColumnInfo(name = "country")
    val country: String = "",

    @ColumnInfo(name = "collectionPrice")
    val collectionPrice: Double
)
