package com.example.saveasmilesurvey

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val startSurveyButton = findViewById<Button>(R.id.startSurveyButton)
        val displayDataButton = findViewById<Button>(R.id.displayDataButton)

        startSurveyButton.setOnClickListener {
            val intent = Intent(this, SurveyType::class.java)
            startActivity(intent)
        }

        displayDataButton.setOnClickListener {
            startActivity(Intent(this, DisplaySurvey::class.java))
        }
    }
}