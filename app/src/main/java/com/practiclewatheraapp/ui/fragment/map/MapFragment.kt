package com.practiclewatheraapp.ui.fragment.map

import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.practiclewatheraapp.R
import com.practiclewatheraapp.databinding.FragmentMapBinding
import com.practiclewatheraapp.source.entity.CityData
import com.practiclewatheraapp.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {
    lateinit var googleMap: GoogleMap
    private val mMapViewModel by viewModels<MapViewModel>()

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

            MaterialAlertDialogBuilder(requireContext())
                .setMessage("Are you sure you want to add this city")
                .setPositiveButton(
                    "Yes"
                ) { dialogInterface, i ->
                    googleMap.clear()
                    googleMap.addMarker(MarkerOptions().position(it))
                    val geocoder = Geocoder(requireContext(), Locale.getDefault())
                    val addresses: List<Address> =
                        geocoder.getFromLocation(it.latitude, it.longitude, 1)
                    val cityName: String = addresses[0].getAddressLine(0)
                    if (cityName != null && cityName.isNotEmpty()) {
                        lifecycleScope.launch {
                            val cityData = CityData(0, it.latitude, it.longitude, cityName)
                            mMapViewModel.saveCityData(cityData)
                        }
                        findNavController().popBackStack()
                    }
                }
                .setNegativeButton(
                    "No"
                ) { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                .show()

        }
    }
}