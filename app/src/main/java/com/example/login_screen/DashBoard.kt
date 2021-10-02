package com.example.login_screen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import java.util.*


class DashBoard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var nametextView: TextView? = null
    var phonetextView: TextView? = null
    var mainNametextView: TextView? = null
    var drawerLayout: DrawerLayout? = null
    var navView: NavigationView? = null
    var progressBar: ProgressBar? = null
    var headerView: View? = null
    var toolBar: androidx.appcompat.widget.Toolbar? = null
    var cname: String? = null
    var ind: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        cname = intent.getStringExtra("Name")
        ind = intent.getStringExtra("ind")
        println(title)

        //Hooks
        progressBar = findViewById((R.id.dashboard_progress_bar))
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_View)
        toolBar = findViewById(R.id.toolbar)
        mainNametextView = findViewById(R.id.textView)
        headerView = navView!!.getHeaderView(0)
        nametextView = headerView!!.findViewById(R.id.nametv)
        phonetextView = headerView!!.findViewById(R.id.phonetv)

        if (ind.equals("fromChildClass")) {
            childDBQuery()
        }


        setSupportActionBar(toolBar)

        navView!!.bringToFront()
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolBar,
            R.string.navogation_drawer_open,
            R.string.navogation_drawer_closed
        )
        drawerLayout!!.addDrawerListener(toggle)
        toggle.syncState()
        val sessionManager =
            SessionManager(this@DashBoard, SessionManager.SESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        val fullName = userDetails[SessionManager.KEY_NAME]
        val Phone_No = userDetails[SessionManager.KEY_PHONE_NO]

        navView!!.setCheckedItem(R.id.nav_home)

        if (ind.equals("fromChildClass")) {
            nametextView!!.setText(cname)
            phonetextView!!.setText("Student")
            mainNametextView!!.setText(cname)
        } else {
            nametextView!!.setText(fullName)
            phonetextView!!.setText("Student")
            mainNametextView!!.setText(fullName)
        }

        progressBar!!.visibility = View.GONE
        navView!!.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else if (ind.equals("fromChildClass")) {
            finish()
        } else {
            moveTaskToBack(true)
            // super.onBackPressed()
        }
    }

    fun callComplaint(view: View) {
        val intent = Intent(applicationContext, Complaints::class.java)
        startActivity(intent)
    }

    fun childDBQuery() {
        progressBar!!.visibility = View.VISIBLE
        //Getting Parent Session
        val sessionManager =
            ParentSessionManager(this@DashBoard, ParentSessionManager.PSESSION_USERSESSION)
        val userDetails: HashMap<String, String> = sessionManager.getUserDetailFromSession()
        var Phone_No = userDetails[ParentSessionManager.PKEY_PHONE_NO]

        //DatabaseQuery
        val checkUser: Query =
            FirebaseDatabase.getInstance().getReference("Children").child(Phone_No!!)
                .child(cname!!)
        checkUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    var email = snapshot.child("email").getValue(String::class.java)
                    var hAddress = snapshot.child("haddress").getValue(String::class.java)
                    var iAddress = snapshot.child("iaddress").getValue(String::class.java)
                    var age = snapshot.child("age").getValue(String::class.java)
                    var gender = snapshot.child("gender").getValue(String::class.java)
                    var phone_number = snapshot.child("phone_No").getValue(String::class.java)
                    var password = snapshot.child("password").getValue(String::class.java)
                    println("$email $cname")
                    //create a Session
                    val sessionManager =
                        SessionManager(this@DashBoard, SessionManager.SESSION_USERSESSION)
                    sessionManager.createLoginSession(
                        cname,
                        email,
                        hAddress,
                        iAddress,
                        age,
                        gender,
                        phone_number,
                        password
                    )

                } else {
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DashBoard, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
    }

    fun callAttendance(view: View) {
        val intent = Intent(applicationContext, Attendance::class.java)
        startActivity(intent)
    }

    fun callSchdule(view: View) {
        val intent = Intent(applicationContext, Schedule::class.java)
        startActivity(intent)
    }

    fun callTrackVan(view: View) {
        val intent = Intent(applicationContext, TrackVan::class.java)
        startActivity(intent)
    }

    fun callFees(view: View) {
        val intent = Intent(applicationContext, Fees::class.java)
        startActivity(intent)
    }

    fun callViewDriver(view: View) {
        val intent = Intent(applicationContext, ViewDriver::class.java)
        startActivity(intent)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> Toast.makeText(this, "Home Button", Toast.LENGTH_SHORT).show()
            R.id.nav_notif -> Toast.makeText(
                this,
                "Notification Button",
                Toast.LENGTH_SHORT
            ).show()
            R.id.nav_setting -> Toast.makeText(this, "Setting Button", Toast.LENGTH_SHORT)
                .show()
            R.id.nav_rateus -> Toast.makeText(this, "Rate Us Button", Toast.LENGTH_SHORT)
                .show()
            R.id.nav_rateDriver -> Toast.makeText(
                this,
                "Rate Driver Button",
                Toast.LENGTH_SHORT
            )
                .show()
            R.id.nav_help -> Toast.makeText(this, "Help Button", Toast.LENGTH_SHORT).show()
            R.id.nav_logout -> {
                Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                val sessionManager =
                    SessionManager(this@DashBoard, SessionManager.SESSION_USERSESSION)
                sessionManager.logoutUserFromSession()
                val sessionManager2 =
                    SessionManager(this@DashBoard, SessionManager.SESSION_REMEMBERME)
                sessionManager2.logoutUserFromSession()

                val intent = Intent(applicationContext, MakeUserSelection::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }
}

