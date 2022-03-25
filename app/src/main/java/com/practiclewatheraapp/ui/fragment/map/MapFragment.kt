package com.practiclewatheraapp.ui.fragment.map

import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(20.5937,78.9629), 5F));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(5F), 2000, null);
        googleMap.setOnMapClickListener {
            googleMap.clear()
            googleMap.addMarker(MarkerOptions().position(it))
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(getString(R.string.lbl_sure_to_add))
                .setPositiveButton(
                    getString(R.string.lbl_yes)
                ) { dialogInterface, i ->
                    dialogInterface.dismiss()
                    val geocoder = Geocoder(requireContext(), Locale.getDefault())
                    val addresses: List<Address> =
                        geocoder.getFromLocation(it.latitude, it.longitude, 1)
                    if (addresses.isNotEmpty()){
                        var cityName =""
                        if (addresses[0].locality!=null &&addresses[0].locality.isNotEmpty()){
                            cityName = addresses[0].locality
                        }else{
                            cityName = addresses[0].getAddressLine(0)
                        }
                        if (cityName.isNotEmpty()) {
                            lifecycleScope.launch {
                                val cityData = CityData(0, it.latitude, it.longitude, cityName)
                                mMapViewModel.saveCityData(cityData)
                            }
                            findNavController().popBackStack()
                        }
                    }
                }
                .setNegativeButton(
                    getString(R.string.lbl_no)
                ) { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                .show()

        }
    }
}