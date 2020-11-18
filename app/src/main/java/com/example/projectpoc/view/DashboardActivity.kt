package com.example.projectpoc.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projectpoc.R

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val intent : Intent = getIntent()
        val userId : Int = intent.getIntExtra("USERID",0)

        Toast.makeText(this, userId.toString(), Toast.LENGTH_SHORT).show()
    }
}