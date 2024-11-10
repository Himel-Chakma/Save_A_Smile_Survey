package com.example.saveasmilesurvey

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class SurveyDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_survey_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val surveyDetailsLayout = findViewById<LinearLayout>(R.id.surveyDetailsLayout)

        val jsonData = intent.getStringExtra("surveyData") ?: return
        val responses = JSONObject(jsonData)

        var questionNumber = 1

        responses.keys().forEach { question ->
            val answer = responses.getString(question)

            // Create TextView for question
            val questionTextView = TextView(this).apply {
                text = "Q$questionNumber: $question"
                textSize = 16f
                setPadding(0, 16, 0, 8)
            }

            // Create TextView for answer
            val answerTextView = TextView(this).apply {
                text = "A: $answer"
                textSize = 14f
                setPadding(0, 0, 0, 16)
            }

            // Add TextViews to layout
            surveyDetailsLayout.addView(questionTextView)
            surveyDetailsLayout.addView(answerTextView)

            questionNumber++
        }
    }
}