package com.recc.recc_client.layout.recyclerview.view_holders

import com.recc.recc_client.databinding.ViewSwimlaneBinding
import com.recc.recc_client.layout.home.HomeViewModel
import com.recc.recc_client.layout.recyclerview.AdapterType
import com.recc.recc_client.layout.recyclerview.DynamicAdapter
import com.recc.recc_client.layout.recyclerview.presenters.PlaylistPresenter
import com.recc.recc_client.layout.recyclerview.presenters.TrackPresenter
import com.recc.recc_client.utils.Alert

private const val MAX_TRACK_PER_SWIMLANE = 10

class PlaylistSwimlaneViewHolder(
    private val binding: ViewSwimlaneBinding,
    private val viewModel: HomeViewModel
): BaseViewHolder(binding.root) {

    private val adapter = DynamicAdapter<TrackPresenter, TracksSwimlaneViewHolder>(
        AdapterType.SWIMLANE_PLAYLIST_TRACKS,
        viewModel)

    fun bind(presenter: PlaylistPresenter) {
        binding.tvTitle.text = presenter.title
        binding.rvContent.adapter = adapter
        Alert("tracks: ${presenter.tracks}")
        if (presenter.tracks.size > MAX_TRACK_PER_SWIMLANE) {
            adapter.submitList(presenter.tracks.subList(0, MAX_TRACK_PER_SWIMLANE))
        } else if (presenter.tracks.isNotEmpty()) {
            adapter.submitList(presenter.tracks)
        }

        // listeners
        binding.clTitleContainer.setOnClickListener {
            viewModel.selectPlaylist(presenter)
        }
    }
}