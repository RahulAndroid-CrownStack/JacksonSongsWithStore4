package com.crownstack.jacksonsongwithstore4.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import com.crownstack.jacksonsongwithstore4.R
import com.crownstack.jacksonsongwithstore4.databinding.FragmentSongListBinding
import com.crownstack.jacksonsongwithstore4.model.SongInfo
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ListFragments"

@AndroidEntryPoint
class ListFragments : Fragment(R.layout.fragment_song_list) {
    private val viewModel by viewModels<ListViewModel>()

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var songsAdapter: SongsAdapter

    lateinit var binding: FragmentSongListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSongListBinding.bind(view)

        binding.apply {
            songsRecycler.apply {
                adapter = songsAdapter
            }
            swipeToRefreshLayout.setOnRefreshListener {
                getLatestSongs()
            }
        }

        getDataFromStore()
        getLatestSongs()

        }

    private fun getDataFromStore() {
        viewModel.songsLiveData.observe(viewLifecycleOwner) {result->
            when {
                result.isSuccess() -> {
                    if (result.data.isNullOrEmpty()) {
                        Toast.makeText(context, "No Songs available", Toast.LENGTH_LONG).show()
                    } else {
                        renderNews(data = result.data)
                        stopRefreshingLayout()
                    }
                }
                result.isLoading() -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Toast.makeText(requireContext(),"Loading..", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Error occured while fetching news", Toast.LENGTH_LONG)
                        .show()
                    binding.progressBar.visibility = View.INVISIBLE
                    stopRefreshingLayout()
                    Toast.makeText(requireContext(),"Error...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getLatestSongs() {
        lifecycleScope.launch {
            viewModel.getNewListOfSongs()
        }
    }

private fun renderNews(data: List<SongInfo>) {
    binding.songsRecycler.adapter = songsAdapter
    binding.progressBar.visibility = View.INVISIBLE
    binding.songsRecycler.visibility = View.VISIBLE
    songsAdapter.submitList(data)
    stopRefreshingLayout()
}

    private fun stopRefreshingLayout() {
        if (binding.swipeToRefreshLayout.isRefreshing) {
            binding.swipeToRefreshLayout.isRefreshing = false
        }
    }

}