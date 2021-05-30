package com.example.login_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout

class PreDashBoard : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_dash_board)


    }

    fun launchDashboard(view: View) {
        val intent = Intent(applicationContext, DashBoard::class.java)
        startActivity(intent)
        finish()
    }
}