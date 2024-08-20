package com.example.gpsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gpsapp.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class Maps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapsBinding
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)


        binding.bntVoltar.setOnClickListener({
            val i = Intent(this,MainActivity::class.java)

            startActivity(i)
        })


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val i = intent

        val bundle = i.extras

        var lat = i.extras!!.getDouble("Latitude")
        var long = i.extras!!.getDouble("Longitude")
        var alt = i.extras!!.getDouble("Altitude")
        var local = i.extras?.getString("Local")
        binding.latitude.text = "Latitude:  " + lat.toString()
        binding.longitude.text = "Longitude:  " + long.toString()
        binding.altitude.text = "Altitude:  " + long.toString()

        // Adiciona um marcador em Sydney e move a câmera
        val location = LatLng(lat , long)
        mMap.addMarker(MarkerOptions().position(location).title("$local"))
        mMap.animateCamera(CameraUpdateFactory.newLatLng(location))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16f))



    }


}