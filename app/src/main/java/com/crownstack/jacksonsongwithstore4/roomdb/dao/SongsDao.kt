package com.crownstack.jacksonsongwithstore4.roomdb.dao

import androidx.room.*
import com.crownstack.jacksonsongwithstore4.model.SongInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface SongsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(songs:List<SongInfo>)

    @Query("SELECT * FROM song_info")
    fun readSongs(): Flow<List<SongInfo>?>

    @Query("delete from song_info")
    suspend fun deleteAll()

    @Transaction
    suspend fun update(songs:List<SongInfo>){
        deleteAll()
        insert(songs)
    }
}