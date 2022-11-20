package com.recc.recc_client.layout.recyclerview.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.recc.recc_client.R
import com.recc.recc_client.databinding.FragmentArtistGridItemBinding

class TrackSwimlaneFragment : Fragment() {
    private lateinit var binding: FragmentArtistGridItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_track_swimlane, container, false)

        return binding.root
    }
}