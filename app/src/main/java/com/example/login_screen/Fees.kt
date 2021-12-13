package com.example.login_screen

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import java.time.LocalDate
import java.util.*

class Fees : AppCompatActivity() {

    var fees: String? = null
    var Phone_No: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fees)


        val sessionManager =
            SessionManager(this@Fees, SessionManager.SESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        val fullName = userDetails[SessionManager.KEY_NAME]
        Phone_No = userDetails[SessionManager.KEY_PHONE_NO]


        println(Phone_No)
        println(fees)

        //DatabaseQuery
        val checkUser: Query = FirebaseDatabase.getInstance().getReference("Students")
            .child(Phone_No!!)
        checkUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                fees = snapshot.child("fee").value as String?
                println(fees)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Fees, "Data Not Exist!", Toast.LENGTH_SHORT).show();
            }
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        println("in loading")

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            // Get String data from Intent
            val ResponseCode = data!!.getStringExtra("pp_ResponseCode")
            println("DateFn: ResponseCode:$ResponseCode")
            if (ResponseCode == "000") {
                //DB Query
                var database: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Students").child(Phone_No!!)
                        .child("fee")
                database.setValue("true")

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)+1
                val day = c.get(Calendar.DAY_OF_MONTH)
                val hour = c.get(Calendar.HOUR_OF_DAY)
                val minute = c.get(Calendar.MINUTE)

                var date: String = LocalDate.of(year!!, month!!, day!!).toString()

                try {
                    val ref = FirebaseDatabase.getInstance().getReference("Fee")
                    val addNewUser = FeeDBHelperClass(
                        "3000",
                        date,
                    )

                    ref.child(Phone_No!!).child(date!!).setValue(addNewUser)


                } catch (e: Exception) {
                    println(e.message)
                }

                Toast.makeText(applicationContext, "Payment Successfull", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Payment Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun callBack(view: View) {
        finish()
    }

    fun feeStatus(view: View) {

        if (fees == "true") {
            Toast.makeText(this@Fees, "Your Fees for this Month is Paid.", Toast.LENGTH_SHORT)
                .show();
        } else {
            Toast.makeText(this@Fees, "Not Paid, Kindly Pay your Fee!!!", Toast.LENGTH_SHORT)
                .show();
            val intent = Intent(applicationContext, PaymentActivity::class.java)
            intent.putExtra("fees", "300000");
            startActivityForResult(intent, 0)
        }
    }


}