package com.example.saveasmilesurvey

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SurveyType : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_survey_type)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val individualSurveyButton = findViewById<Button>(R.id.individualSurveyButton)
        val groupSurveyButton = findViewById<Button>(R.id.groupSurveyButton)

        individualSurveyButton.setOnClickListener {
            val intent = Intent(this, ActivitySurvey::class.java)
            startActivity(intent)
        }

        groupSurveyButton.setOnClickListener {
            val intent = Intent(this, GroupSurvey::class.java)
            startActivity(intent)
        }
    }
}