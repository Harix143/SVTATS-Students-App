package com.example.login_screen

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import com.google.firebase.database.*
import java.util.*

class ViewDriver : AppCompatActivity() {

    var callBack: Button? = null
    var view_Driver: TextView? = null
    var id: Int = 0
    var ind: Int = 0
    var phone_No: String? = null
    var van_number: String? = null
    var driver_name: String? = null
    var driver_phone_No: String? = null
    var model: String? = null
    var ac: String? = null
    var capacity: String? = null
    var view: View? = null
    private val Request_Call = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_driver)
        view_Driver = findViewById(R.id.view_Driver)
        view = findViewById(R.id.view)
        val sessionManager =
            SessionManager(this@ViewDriver, SessionManager.SESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        phone_No = userDetails[SessionManager.KEY_PHONE_NO]

        //DatabaseQuery
        val checkUser: Query =
            FirebaseDatabase.getInstance().getReference("Students").child(phone_No!!)
                .child("Driver")
        checkUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    driver_phone_No = snapshot.getValue(String::class.java)
                    println(driver_phone_No)

                    val checkDriver: Query =
                        FirebaseDatabase.getInstance().getReference("Drivers").child(snapshot.getValue(String::class.java)!!)
                    checkDriver.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dsnapshot: DataSnapshot) {
                            if (dsnapshot.exists()) {
                                van_number = dsnapshot.child("Van").getValue(String::class.java)
                                driver_name = dsnapshot.child("fullName").getValue(String::class.java)

                                val checkVan: Query =
                                    FirebaseDatabase.getInstance().getReference("Vans").child(dsnapshot.child("Van").getValue(String::class.java)!!)
                                checkVan.addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(vsnapshot: DataSnapshot) {
                                        if (vsnapshot.exists()) {
                                            ac = vsnapshot.child("ac").getValue(String::class.java)
                                            capacity = vsnapshot.child("capacity").getValue(String::class.java)
                                            model = vsnapshot.child("model").getValue(String::class.java)

                                            val s = SpannableStringBuilder()
                                                .bold { append("Vehicle No: ") }
                                                .append(van_number)
                                                .bold { append("\nDriver Name: ") }
                                                .append(driver_name)
                                                .bold { append("\n" + "Model: ") }
                                                .append(model)
                                                .bold { append("\n" + "Air-Condition: ") }
                                                .append(ac)
                                                .bold { append("\n" + "Capacity: ") }
                                                .append(capacity)
                                                .append("\n")
                                            view_Driver!!.append(s)

                                        } else {
                                            ind++;
                                        }
                                    }
                                    override fun onCancelled(error: DatabaseError) {
                                        Toast.makeText(this@ViewDriver, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                })
                            } else {
                                ind++;
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(this@ViewDriver, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })

                } else {
                    ind++;
                    Toast.makeText(this@ViewDriver, "No Driver assigned yet!", Toast.LENGTH_SHORT)
                        .show();
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewDriver, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
    }

    fun callBack(view: View) {
        finish()
    }


    fun makecalldriver(view: View) {
        makeDriverCall()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == Request_Call) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeDriverCall();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun makeDriverCall() {
        if (driver_phone_No!!.trim().length > 0) {
            if (ContextCompat.checkSelfPermission(
                    this@ViewDriver,
                    android.Manifest.permission.CALL_PHONE
                ) != (PackageManager.PERMISSION_GRANTED)
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    Request_Call
                )
            } else {
                val dial = "tel:"+driver_phone_No
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }
        }
    }
}