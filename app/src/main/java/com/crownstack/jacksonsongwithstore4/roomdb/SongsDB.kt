package com.crownstack.jacksonsongwithstore4.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crownstack.jacksonsongwithstore4.model.SongInfo
import com.crownstack.jacksonsongwithstore4.roomdb.dao.SongsDao

@Database(entities = [SongInfo::class],version = 1)
abstract class SongsDB:RoomDatabase() {
    abstract fun dao():SongsDao
}