package com.crownstack.jacksonsongwithstore4.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.crownstack.jacksonsongwithstore4.databinding.ItemSongListBinding
import com.crownstack.jacksonsongwithstore4.model.SongInfo

class SongsAdapter(private val imageLoader: ImageLoader) :
    ListAdapter<SongInfo, SongsAdapter.SongsHolder>(Difference()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SongsHolder(ItemSongListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SongsHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class SongsHolder(private val binding: ItemSongListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(songInfo: SongInfo) {
            with(songInfo) {
                binding.textPrice.text = "$collectionPrice $"
                binding.textAlbumName.text = artistName
                binding.textDesc.text = "$artistId"
                binding.imagePoster.load(artworkUrl100, imageLoader)
            }
        }

    }

    private class Difference : DiffUtil.ItemCallback<SongInfo>() {
        override fun areItemsTheSame(oldItem: SongInfo, newItem: SongInfo) =
            oldItem.trackId == newItem.trackId

        override fun areContentsTheSame(oldItem: SongInfo, newItem: SongInfo) = oldItem == newItem
    }


}