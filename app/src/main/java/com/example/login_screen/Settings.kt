package com.example.login_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun callBack(view: View) {
        finish()
    }
    fun changePassword(view: View) {
        var ind = intent.getStringExtra("ind")
        if (ind.equals("fromChildClass")) {
            val intent = Intent(applicationContext, ChangePassword::class.java)
            intent.putExtra("ind","fromChildClass")
            startActivity(intent)
        }
        else
        {
            val intent = Intent(applicationContext, ChangePassword::class.java)
            startActivity(intent)
        }

    }
}