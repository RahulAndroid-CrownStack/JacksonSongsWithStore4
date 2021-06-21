package com.crownstack.jacksonsongwithstore4.ui.fragments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crownstack.jacksonsongwithstore4.model.SongInfo
import com.crownstack.jacksonsongwithstore4.model.SongsResult
import com.crownstack.jacksonsongwithstore4.network.SongsRepo
import kotlinx.coroutines.flow.collect

class ListViewModel @ViewModelInject constructor(
    private val repository: SongsRepo
):ViewModel() {
    private val _songsLiveData = MutableLiveData<SongsResult<List<SongInfo>>>()
    var songsLiveData: LiveData<SongsResult<List<SongInfo>>> = _songsLiveData

    suspend fun getNewListOfSongs() {
        repository.getLatestListOfSongs().collect {
            _songsLiveData.value = it
            songsLiveData = _songsLiveData
        }
    }
}