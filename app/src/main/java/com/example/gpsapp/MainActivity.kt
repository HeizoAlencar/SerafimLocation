package com.example.gpsapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.health.connect.datatypes.ExerciseRoute
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.gpsapp.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.logging.ErrorManager

private lateinit var fusedLocationClient: FusedLocationProviderClient
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding;
    private val PERMISSION_REQUEST_CODE = 1000




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);

        // Verifica se a permissão de localização foi concedida
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Solicita a permissão de localização
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.imageButton.setOnClickListener({
            startActivity(Intent(this,Maps::class.java))
        })

        binding.imageButton.setOnClickListener({
                try {
                    val longitude: Double = binding.latitude.text.toString().toDouble()
                    val latitude: Double = binding.longitude.text.toString().toDouble()
                    val i = Intent(this, Maps::class.java)
                    i.putExtra("Longitude", longitude)
                        .putExtra("Latitude", latitude)
                        .putExtra("Altitude", 0.0)

                    startActivity(i)
                }catch (e: NumberFormatException ){
                    Toast.makeText(this, "Formato Invalido",Toast.LENGTH_LONG)
                }














        })



        binding.bntEtec.setOnClickListener({
            val i = Intent(this, Maps::class.java)
            i.putExtra("Longitude",-47.3148421)
                .putExtra("Latitude",-22.8032474)
                .putExtra("Altitude",0.0)
                .putExtra("Local","Etec Ferrucio Humberto Gazzeta")


            startActivity(i)
        })


        binding.BntOndeEstou.setOnClickListener({
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Verifica se a localização não é nula
                    if (location != null) {

                        val  latitude: Double  = location.latitude
                        val longitude: Double = location.longitude
                        val altitude: Double = location.altitude

                        val i = Intent(this, Maps::class.java)
                        i.putExtra("Longitude",longitude)
                            .putExtra("Latitude",latitude)
                            .putExtra("Altitude",altitude)


                        startActivity(i)
                        // Use a latitude e longitude conforme necessário
                    } else {
                     Toast.makeText(this, "Não foi possivel obter a localização.",Toast.LENGTH_LONG)
                    }
                }
                .addOnFailureListener { exception ->
                    // Tratar falhas na obtenção da localização
                    Toast.makeText(this, "Falha ao obter localização: ${exception.message}", Toast.LENGTH_SHORT).show()
                }








        })










    }
}

private fun FusedLocationProviderClient.getCurrentLocation(function: (ExerciseRoute.Location?) -> Unit) {

}

private fun FusedLocationProviderClient.getLastLocation(function: (ExerciseRoute.Location?) -> Unit) {

}
