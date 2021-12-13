package com.example.login_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.HashMap
import java.util.regex.Matcher
import java.util.regex.Pattern

class ChangePassword : AppCompatActivity() {

    var old_pass: TextInputLayout? = null
    var new_pass: TextInputLayout? = null
    var con_pass: TextInputLayout? = null
    var _id: Int = 0
    var Phone_No: String? = null
    var password: String? = null
    var name: String? = null
    var ind: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        old_pass = findViewById((R.id.old_pass))
        new_pass = findViewById((R.id.new_pass))
        con_pass = findViewById((R.id.con_pass))
        ind = intent.getStringExtra("ind")

        val sessionManager =
            SessionManager(this@ChangePassword, SessionManager.SESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        val fullName = userDetails[SessionManager.KEY_NAME]
        Phone_No = userDetails[SessionManager.KEY_PHONE_NO]
        name = userDetails[SessionManager.KEY_NAME]
        password = userDetails[SessionManager.KEY_PASSWORD]

    }

    fun callBack(view: View) {
        finish()
    }

    fun UpdatePassword(view: View) {
        if (!checkCurrent() || !validateNewPassword() || !checkSame()) {
            return
        }

        val new_pass = new_pass!!.getEditText()!!.getText().toString().trim()

        if (ind.equals("fromChildClass")) {
            //Update in the Database
            var cdatabase: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("Children")
            cdatabase.child(Phone_No!!).child(name!!).child("password").setValue(new_pass)
            var sdatabase: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("Students")
            sdatabase.child(Phone_No!!).child("password").setValue(new_pass)
            var pdatabase: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("Parents")
            pdatabase.child(Phone_No!!).child("password").setValue(new_pass)
        } else {
            //Update in the Database
            var database: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("Students")
            database.child(Phone_No!!).child("password").setValue(new_pass)

        }
        Toast.makeText(this, "Password Changed!", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
        val sessionManager =
            SessionManager(this@ChangePassword, SessionManager.SESSION_USERSESSION)
        sessionManager.logoutUserFromSession()
        val sessionManager2 =
            SessionManager(this@ChangePassword, SessionManager.SESSION_REMEMBERME)
        sessionManager2.logoutUserFromSession()

        val psessionManager =
            ParentSessionManager(this@ChangePassword, ParentSessionManager.PSESSION_USERSESSION)
        psessionManager.logoutUserFromSession()
        val psessionManager2 =
            ParentSessionManager(this@ChangePassword, ParentSessionManager.PSESSION_REMEMBERME)
        psessionManager2.logoutUserFromSession()

        val intent = Intent(applicationContext, MakeUserSelection::class.java)
        startActivity(intent)
        finish()

    }

    private fun validateNewPassword(): Boolean {
        val `val` = new_pass!!.getEditText()!!.getText().toString().trim()
        val conf_pass = con_pass!!.getEditText()!!.getText().toString().trim()


        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(`val`)

        // return matcher.matches()
        if (`val`.isEmpty()) {
            new_pass!!.setError("Field can not be empty")
            return false
        } else if (!matcher.matches()) {
            new_pass!!.setError("Invalid Password Format!")
            return false
        } else if (`val`.length < 9) {
            new_pass!!.setError("Password should contain atleast 8 characters!")
            return false
        } else {
            new_pass!!.setError(null)
            new_pass!!.setErrorEnabled(false)
            return true
        }
    }

    private fun checkSame(): Boolean {
        val `val` = con_pass!!.getEditText()!!.getText().toString().trim()
        val new_pass = new_pass!!.getEditText()!!.getText().toString().trim()

        if (!`val`.equals(new_pass)) {
            con_pass!!.setError("Password Must be Same!!!")
            return false
        } else {
            con_pass!!.setError(null)
            con_pass!!.setErrorEnabled(false)
            return true
        }
    }

    private fun checkCurrent(): Boolean {
        var new_passs = old_pass!!.getEditText()!!.getText().toString().trim()

        if (!password.equals(new_passs)) {
            old_pass!!.setError("Password not Matched!!!")
            return false
        } else {
            old_pass!!.setError(null)
            old_pass!!.setErrorEnabled(false)
            return true
        }
    }
}