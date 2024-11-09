package com.example.saveasmilesurvey

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject
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

        val surveyFiles = filesDir.listFiles { _, name -> name.startsWith("survey_data_") }
        val fileNames = surveyFiles?.map { it.name } ?: emptyList()
        val surveyListView = findViewById<ListView>(R.id.surveyListView)
        val displayDataTextView = findViewById<TextView>(R.id.displayDataTextView)

        if (fileNames.isNotEmpty()) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fileNames)
            surveyListView.adapter = adapter

            surveyListView.setOnItemClickListener { _, _, position, _ ->
                val selectedFile = File(filesDir, fileNames[position])
                val jsonData = selectedFile.readText()
                val responses = JSONObject(jsonData)
                displayDataTextView.text = responses.toString(4)
            }
        } else {
            displayDataTextView.text = "No previous survey data found."
        }
    }
}