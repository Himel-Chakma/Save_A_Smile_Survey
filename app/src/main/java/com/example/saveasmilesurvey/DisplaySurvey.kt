package com.example.saveasmilesurvey

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class DisplaySurvey : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_display_survey)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val surveyFiles = filesDir.listFiles { _, name ->
            name.startsWith("survey_data_") || name.startsWith("group_survey_")
        }
        val fileNames = surveyFiles?.map { it.name } ?: emptyList()
        val surveyListView = findViewById<ListView>(R.id.surveyListView)
        val displayDataTextViewSelect = findViewById<TextView>(R.id.displayDataTextViewSelect)

        if (fileNames.isNotEmpty()) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fileNames)
            surveyListView.adapter = adapter

            surveyListView.setOnItemClickListener { _, _, position, _ ->
                val selectedFile = File(filesDir, fileNames[position])
                val jsonData = selectedFile.readText()

                // Start SurveyDetailActivity and pass the JSON data
                val intent = Intent(this, SurveyDetails::class.java)
                intent.putExtra("surveyData", jsonData)
                startActivity(intent)
            }
        } else {
            displayDataTextViewSelect.text = "No previous survey data found."
        }
    }
}