package com.example.login_screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import com.example.login_screen.databinding.ActivityTrackingBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.database.*
import java.util.HashMap


class TrackingActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityTrackingBinding
    private var dbref: DatabaseReference? = null
    private var myDbref: DatabaseReference? = null
    private var marker: Marker? = null
    private var myMarker: Marker? = null
    private var lat: Double = 33.6567
    private var lon: Double = 73.1598
    private var mlat: Double = 33.6567
    private var mlon: Double = 73.1598
    private val MIN_TIME: Long = 2000 // 1 Sec
    private val MIN_DIS: Float = 1.0F // 1 Meter
    private var driver_phone_No: String? = null
    private var phone_No: String? = null

    private var manager: LocationManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sessionManager =
            SessionManager(this@TrackingActivity, SessionManager.SESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        phone_No = userDetails[SessionManager.KEY_PHONE_NO]

        driver_phone_No = intent.getStringExtra("driver")
        var ind: String = intent.getStringExtra("inda")
        if (ind == "no") {
            Toast.makeText(this, "No Driver Assigned Yet!", Toast.LENGTH_SHORT).show()
            finish()
        } else {

            binding = ActivityTrackingBinding.inflate(layoutInflater)
            setContentView(binding.root)
            manager = getSystemService(LOCATION_SERVICE) as LocationManager


//        val sessionManager =
//            SessionManager(this@TrackingActivity, SessionManager.SESSION_USERSESSION)
//        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
//        val fullName = userDetails[SessionManager.KEY_NAME]
//        val Phone_No = userDetails[SessionManager.KEY_PHONE_NO]
//
//
//
//        myDbref = FirebaseDatabase.getInstance().getReference("Students").child(Phone_No!!).child("location");
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
            dbref = FirebaseDatabase.getInstance().getReference("Drivers").child(driver_phone_No!!)
                .child("location");
            readChanges()
            getLocationUpdates()
        }
    }

    private fun readChanges() {
        dbref!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                    try {
                        run {
                            var dl: DriverLocation? = snapshot.getValue(DriverLocation::class.java)

                            if (dl != null) {
                                marker!!.setPosition(LatLng(dl.latitude, dl.longitude))
                                lat = dl.latitude;
                                lon = dl.longitude;
                            }
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@TrackingActivity, e.toString(), Toast.LENGTH_SHORT)
                            .show();
                    }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TrackingActivity, error.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            }
        })
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        // val islamabad = LatLng(33.6844, 73.0479)
        var islamabad = LatLng(lat, lon)
        var myLoc = LatLng(mlat, mlon)
        marker = mMap.addMarker(
            MarkerOptions().position(islamabad).title("Driver is Here!")
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.marker_bus_icon_map))
        )
        myMarker = mMap.addMarker(MarkerOptions().position(myLoc).title("My Location!!"))
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.setAllGesturesEnabled(true)
        mMap.isTrafficEnabled = true;
        mMap.uiSettings.isCompassEnabled = true
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 15f))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(islamabad, 15f))
    }

    private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        // below line is use to generate a drawable.
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        // below line is use to set bounds to our vector drawable.
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        // below line is use to create a bitmap for our
        // drawable which we have added.
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // below line is use to add bitmap in our canvas.
        val canvas = Canvas(bitmap)

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas)

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onLocationChanged(location: Location?) {
        if (location != null) {
            savLocation(location);
        } else {
            Toast.makeText(this, "No Location", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("Not yet implemented")
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("Not yet implemented")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocationUpdates()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101
                )
//                Toast.makeText(this,"Permission Required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getLocationUpdates() {
        if (manager != null) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                if (manager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    manager!!.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME,
                        MIN_DIS,
                        this
                    )
                } else if (manager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    manager!!.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME,
                        MIN_DIS,
                        this
                    )
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101
                    )
                    Toast.makeText(this, "Please Turn ON Location", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101
                )
            }
        }
    }


    private fun savLocation(p0: Location) {
        myMarker!!.setPosition(LatLng(p0.latitude, p0.longitude))
        mlat = p0.latitude;
        mlon = p0.longitude
    }
}