package com.example.login_screen

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class Create_Schedule : AppCompatActivity() {

    var mon_pickup: Button? = null
    var tue_pickup: Button? = null
    var wed_pickup: Button? = null
    var thu_pickup: Button? = null
    var fri_pickup: Button? = null
    var sat_pickup: Button? = null

    var mon_dropoff: Button? = null
    var tue_dropoff: Button? = null
    var wed_dropoff: Button? = null
    var thu_dropoff: Button? = null
    var fri_dropoff: Button? = null
    var sat_dropoff: Button? = null

    var hour:Int = 0
    var mint:Int = 0
    var Phone_No: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_schedule)


        //hooks
        mon_pickup = findViewById((R.id.mon_pickup))
        tue_pickup = findViewById((R.id.tue_pickup))
        wed_pickup = findViewById((R.id.wed_pickup))
        thu_pickup = findViewById((R.id.thu_pickup))
        fri_pickup = findViewById((R.id.fri_pickup))
        sat_pickup = findViewById((R.id.sat_pickup))

        mon_dropoff = findViewById((R.id.mon_dropoff))
        tue_dropoff = findViewById((R.id.tue_dropoff))
        wed_dropoff = findViewById((R.id.wed_dropoff))
        thu_dropoff = findViewById((R.id.thu_dropoff))
        fri_dropoff = findViewById((R.id.fri_dropoff))
        sat_dropoff = findViewById((R.id.sat_dropoff))


        val sessionManager =
            SessionManager(this@Create_Schedule, SessionManager.SESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        val fullName = userDetails[SessionManager.KEY_NAME]
        Phone_No = userDetails[SessionManager.KEY_PHONE_NO]



    }

    fun callBack(view: View) {
        finish()
    }
    fun mon_pickup(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                mon_pickup!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    fun tue_pickup(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                tue_pickup!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    fun tue_dropoff(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                tue_dropoff!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    fun wed_pickup(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                wed_pickup!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    fun thu_pickup(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                thu_pickup!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    fun thu_dropoff(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                thu_dropoff!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    fun fri_pickup(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                fri_pickup!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    fun sat_pickup(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                sat_pickup!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    fun sat_dropoff(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                sat_dropoff!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    fun fri_dropoff(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                fri_dropoff!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    fun wed_dropoff(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                wed_dropoff!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    fun mon_dropoff(view: View) {
        val onTimeSetListener =
            OnTimeSetListener { view, selectedHour, selectedMint ->
                hour = selectedHour
                mint = selectedMint
                mon_dropoff!!.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, mint))
            }
        val timePickerDialog = TimePickerDialog(this, onTimeSetListener, hour, mint, false)

        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }

    
    fun saveSchedule(view: View) {
        storeScheduleData()

        val intent = Intent(applicationContext, Schedule::class.java)
        startActivity(intent)
        finish()

    }

    private fun storeScheduleData() {
        try {
            val ref = FirebaseDatabase.getInstance().getReference("Schedule")
            val addNewUser = ScheduleDBHelperClass(
                mon_pickup!!.getText().toString(),
                mon_dropoff!!.getText().toString(),
                "No",

                tue_pickup!!.getText().toString(),
                tue_dropoff!!.getText().toString(),
                "No",

                wed_pickup!!.getText().toString(),
                wed_dropoff!!.getText().toString(),
                "No",

                thu_pickup!!.getText().toString(),
                thu_dropoff!!.getText().toString(),
                "No",

                fri_pickup!!.getText().toString(),
                fri_dropoff!!.getText().toString(),
                "No",

                sat_pickup!!.getText().toString(),
                sat_dropoff!!.getText().toString(),
                "No"
            )
            //val id = ref.push().key
            ref.child(Phone_No!!).setValue(addNewUser)
            Toast.makeText(this, "Schedule Stored Successfully!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            println(e.message)
        }
    }
}