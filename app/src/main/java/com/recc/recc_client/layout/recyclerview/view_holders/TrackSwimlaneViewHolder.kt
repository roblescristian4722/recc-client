package com.recc.recc_client.layout.recyclerview.view_holders

import com.recc.recc_client.databinding.FragmentTrackSwimlaneBinding
import com.recc.recc_client.layout.home.HomeViewModel
import com.recc.recc_client.layout.recyclerview.presenters.PlaylistPresenter

class TrackSwimlaneViewHolder(
    private val binding: FragmentTrackSwimlaneBinding,
    private val viewModel: HomeViewModel
): BaseViewHolder(binding.root) {

    fun bind(presenter: PlaylistPresenter) {
        binding.tvTitle.text = presenter.title

        // listeners
        binding.clTitleContainer.setOnClickListener {
            // TODO: navigates to PlaylistView
        }
    }
}