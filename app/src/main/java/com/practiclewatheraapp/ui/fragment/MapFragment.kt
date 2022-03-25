package com.practiclewatheraapp.ui.fragment

import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.practiclewatheraapp.R
import com.practiclewatheraapp.databinding.FragmentMapBinding

class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {
    lateinit var googleMap: GoogleMap

    override fun onReady() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

    }

    override fun findContentView() = R.layout.fragment_map

    override fun setViewModelObservers() {

    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0

        googleMap.setOnMapClickListener {
            googleMap.clear()
            googleMap.addMarker(MarkerOptions().position(it))

        }
    }
}