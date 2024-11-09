package com.example.saveasmilesurvey

import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import org.json.JSONObject
import java.io.File
import java.util.Date

class ActivitySurvey : AppCompatActivity() {
    private val questions = listOf(
        "What menstrual hygiene products do you use?",
        "How often do you change your menstrual hygiene product on heavy flow days?",
        "How often do you change your menstrual hygiene product on light flow days?"
    )

    private val options = listOf(
        listOf("Pads", "Cloth Pads", "Others"), // for Question 1
        listOf("Every 1-2 Hours", "Every 3-5 Hours", "More than 6 Hours"), // for Question 2
        listOf("Every 1-2 Hours", "Every 3-5 Hours", "More than 6 Hours")  // for Question 3
    )

    private var currentQuestionIndex = 0
    private val responses = JSONObject()

    // Declare views here without initializing them
    private lateinit var questionNumberText: TextView
    private lateinit var questionText: TextView
    private lateinit var optionGroup: RadioGroup
    private lateinit var nextButton: Button
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        // Initialize views here after setContentView
        questionNumberText = findViewById(R.id.questionNumberText)
        questionText = findViewById(R.id.questionText)
        optionGroup = findViewById(R.id.optionGroup)
        nextButton = findViewById(R.id.nextButton)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)

        // Set up edge-to-edge mode (optional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadQuestion()

        nextButton.setOnClickListener {
            val selectedOptionId = optionGroup.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedOption = findViewById<RadioButton>(selectedOptionId).text.toString()
                responses.put(questions[currentQuestionIndex], selectedOption)

                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                    loadQuestion()
                } else {
                    saveResponses()
                    finish() // End survey
                }
            }
        }
    }

    private fun loadQuestion() {
        questionNumberText.text = "Question: ${currentQuestionIndex + 1}/${questions.size}"
        questionText.text = questions[currentQuestionIndex]
        val currentOptions = options[currentQuestionIndex]

        option1.text = currentOptions.getOrNull(0)
        option2.text = currentOptions.getOrNull(1)
        option3.text = currentOptions.getOrNull(2)
        option4.text = currentOptions.getOrNull(3)

        option1.isVisible = option1.text.isNotEmpty()
        option2.isVisible = option2.text.isNotEmpty()
        option3.isVisible = option3.text.isNotEmpty()
        option4.isVisible = option4.text.isNotEmpty()

        optionGroup.clearCheck()

        if (currentQuestionIndex == questions.size - 1) {
            nextButton.text = "Submit"
        } else {
            nextButton.text = "Next"
        }
    }

    private fun saveResponses() {
        // Generate a unique filename based on the date and time
        val timestamp = DateFormat.format("yyyyMMdd_hhmmss", Date()).toString()
        val fileName = "survey_data_$timestamp.json"
        val file = File(filesDir, fileName)

        // Save the responses as JSON
        file.writeText(responses.toString())
    }
}
