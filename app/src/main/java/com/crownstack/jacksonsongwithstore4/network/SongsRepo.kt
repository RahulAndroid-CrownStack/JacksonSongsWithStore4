package com.crownstack.jacksonsongwithstore4.network

import android.util.Log
import com.crownstack.jacksonsongwithstore4.model.SongsResult
import com.crownstack.jacksonsongwithstore4.model.SongInfo
import com.crownstack.jacksonsongwithstore4.roomdb.SongsDB
import com.dropbox.android.external.store4.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

private const val TAG = "SongsRepo"

class SongsRepo @Inject constructor(
    private val songsDB: SongsDB,
    private val api: SongsApi
) {
    private val songsStore: Store<String, List<SongInfo>> = StoreBuilder.from(
        fetcher = Fetcher.of {
            api.getSongList("Michael+jackson")
        },
        sourceOfTruth = SourceOfTruth.Companion.of(
            reader = { songsDB.dao().readSongs() },
            writer = { _: String, input: SongResponse ->
                songsDB.dao().update(input.results)
            }
        )
    ).build()


    suspend fun getLatestListOfSongs(): Flow<SongsResult<List<SongInfo>>> =
        flow {
            songsStore.stream(StoreRequest.cached(key = "songs_cache", refresh = true))
                .flowOn(Dispatchers.IO)
                .collect { response: StoreResponse<List<SongInfo>> ->
                    when (response) {
                        is StoreResponse.Loading -> {
                            print("[Store 4] Loading from ${response.origin} \n")
                            emit(SongsResult.loading<List<SongInfo>>())
                        }
                        is StoreResponse.Data -> {
                            print("[Store 4] Data from ${response.origin}  with ${response.value.size} elements \n")
                            Log.d(TAG, "getLatestListOfSongs: ${Gson().toJson(response.value)}\n")
                            emit(SongsResult.success(response.requireData()))
                        }
                        is StoreResponse.NoNewData -> {
                            emit(SongsResult.success(emptyList<SongInfo>()))
                        }
                        is StoreResponse.Error.Exception -> {
                            Log.d(TAG, "getLatestNews: ${response.error.localizedMessage}\n")

                            emit(SongsResult.error<List<SongInfo>>())
                        }
                        is StoreResponse.Error.Message -> {
                            print("[Store 4] Error from  ${response.origin}  \n")
                            emit(SongsResult.error<List<SongInfo>>())
                        }
                    }
                }
        }.flowOn(Dispatchers.IO)
}