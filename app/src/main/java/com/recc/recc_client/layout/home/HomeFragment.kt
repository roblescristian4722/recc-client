package com.recc.recc_client.layout.home

import android.content.Intent
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.recc.recc_client.MainActivity
import com.recc.recc_client.R
import com.recc.recc_client.WebViewActivity
import com.recc.recc_client.databinding.FragmentHomeBinding
import com.recc.recc_client.layout.common.BaseFragment
import com.recc.recc_client.layout.common.Event
import com.recc.recc_client.layout.recyclerview.AdapterType
import com.recc.recc_client.layout.recyclerview.DynamicAdapter
import com.recc.recc_client.layout.recyclerview.presenters.PlaylistPresenter
import com.recc.recc_client.layout.recyclerview.view_holders.PlaylistSwimlaneViewHolder
import com.recc.recc_client.utils.SharedPreferences
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeScreenEvent, HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModel()
    private val sharedPreferences: SharedPreferences by inject()
    private var adapter: DynamicAdapter<PlaylistPresenter, PlaylistSwimlaneViewHolder>? = null

    override fun subscribeToViewModel() {
        binding.swlPlaylists.setOnRefreshListener {
            viewModel.getPlaylists()
            swl_playlists.isRefreshing = false
        }
        if (sharedPreferences.getSpotifyStatus()) {
            (requireActivity() as MainActivity).loginToSpotify()
        }
        viewModel.screenEvent.observe(viewLifecycleOwner, Event.EventObserver { screenEvent ->
            when (screenEvent) {
                HomeScreenEvent.PlaylistSelected -> {
                    viewModel.selectedPlaylist.value?.let {
                        val bundle = bundleOf(
                            "id" to it.id,
                            "title" to it.title)
                        findNavController().navigate(R.id.action_pagerFragment_to_playlistSongsFragment, bundle)
                    }
                }
                is HomeScreenEvent.TracksFetched -> {
                    adapter?.submitList(screenEvent.presenters)
                }
                HomeScreenEvent.GetSpotifyToken -> {
                    val intent = Intent(requireContext(), WebViewActivity::class.java)
                    startActivity(intent)
                }
                HomeScreenEvent.CreateCustomPlaylistButtonPressed -> {
                    findNavController().navigate(R.id.action_pagerFragment_to_selectCustomPlaylistTracks)
                }
            }
        })

        adapter = DynamicAdapter(AdapterType.SWIMLANE_PLAYLIST, viewModel)
        binding.rvHomePlaylist.rv_home_playlist.adapter = adapter
        viewModel.getPlaylists()
    }
}