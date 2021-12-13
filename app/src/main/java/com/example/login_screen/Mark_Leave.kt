package com.example.login_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.HashMap

class Mark_Leave : AppCompatActivity() {

    var mon_leave: CheckBox? = null
    var tue_leave: CheckBox? = null
    var wed_leave: CheckBox? = null
    var thu_leave: CheckBox? = null
    var fri_leave: CheckBox? = null
    var sat_leave: CheckBox? = null

    var Phone_No: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mark_leave)

        mon_leave = findViewById((R.id.mon_lea))
        tue_leave = findViewById((R.id.tue_lea))
        wed_leave = findViewById((R.id.wed_lea))
        thu_leave = findViewById((R.id.thu_lea))
        fri_leave = findViewById((R.id.fri_lea))
        sat_leave = findViewById((R.id.sat_lea))

        val sessionManager =
            SessionManager(this@Mark_Leave, SessionManager.SESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        val fullName = userDetails[SessionManager.KEY_NAME]
        Phone_No = userDetails[SessionManager.KEY_PHONE_NO]


    }

    fun callBack(view: View) {
        finish()
    }

    fun saveLeave(view: View) {
        try {
            if (mon_leave!!.isChecked) {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("mon_leave")
                database.setValue("Yes")
            } else {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("mon_leave")
                database.setValue("No")
            }
            if (tue_leave!!.isChecked) {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("tue_leave")
                database.setValue("Yes")
            } else {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("tue_leave")
                database.setValue("No")
            }
            if (wed_leave!!.isChecked) {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("wed_leave")
                database.setValue("Yes")
            } else {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("wed_leave")
                database.setValue("No")
            }
            if (thu_leave!!.isChecked) {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("thu_leave")
                database.setValue("Yes")
            } else {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("thu_leave")
                database.setValue("No")
            }
            if (fri_leave!!.isChecked) {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("fri_leave")
                database.setValue("Yes")
            } else {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("fri_leave")
                database.setValue("No")
            }
            if (sat_leave!!.isChecked) {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("sat_leave")
                database.setValue("Yes")
            } else {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Schedule").child(Phone_No!!)
                        .child("sat_leave")
                database.setValue("No")
            }
        } catch (e: Exception) {
            println(e.message)
        }

        val intent = Intent(applicationContext, Schedule::class.java)
        startActivity(intent)
        finish()
    }
}