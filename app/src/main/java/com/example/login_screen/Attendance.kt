package com.example.login_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.ArrayList
import java.util.HashMap

class Attendance : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var adapter: AttendanceAdapterClass? = null

    var Phone_No: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)




        val sessionManager =
            SessionManager(this@Attendance, SessionManager.SESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        val fullName = userDetails[SessionManager.KEY_NAME]
        Phone_No = userDetails[SessionManager.KEY_PHONE_NO]

        recyclerView = findViewById((R.id.arecycler_View))
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))

        val holder = ArrayList<AttendanceModel>()

        //DataBase Query
        val checkUser: Query =
            FirebaseDatabase.getInstance().getReference("Attendance").child(Phone_No!!)
        checkUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()) {
                    for (childDS in snapshot.children) {

                        //progressBar!!.visibility = View.VISIBLE
                        val cm = AttendanceModel()
                        var obj = childDS.getValue(AttendanceDBHelperClass::class.java)
                        cm.setDate(obj!!.date)
                        cm.setPickup(obj!!.pickup)
                        cm.setDropoff(obj!!.dropoff)
                        holder.add(cm)
                        adapter = AttendanceAdapterClass(holder, applicationContext)
                        recyclerView!!.setAdapter(adapter)
                    }
                }
                else{
                    Toast.makeText(this@Attendance, "No Attendance Submit Yet!!", Toast.LENGTH_SHORT).show();
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Attendance, error.message, Toast.LENGTH_SHORT).show();
            }
        })
    }

    fun callBack(view: View) {
        finish()
    }
}