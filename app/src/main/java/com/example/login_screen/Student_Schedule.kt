package com.example.login_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Student_Schedule : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_schedule)
    }

    fun callBack(view: View) {
        finish()
    }
    fun create_Schedule(view: View) {
        val intent = Intent(applicationContext, Create_Schedule::class.java)
        startActivity(intent)
    }
    fun view_Schedule(view: View) {
        val intent = Intent(applicationContext, Schedule::class.java)
        startActivity(intent)
    }
    fun mark_leave(view: View) {
        val intent = Intent(applicationContext, Mark_Leave::class.java)
        startActivity(intent)
    }
}