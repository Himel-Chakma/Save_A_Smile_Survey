package com.example.saveasmilesurvey

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject
import java.io.File

class GroupSurvey : AppCompatActivity() {
    private val questions = listOf(
        "তুমি কী ধরণের মাসিক স্বাস্থ্যবিধি দ্রব্য ব্যবহার করে থাকো? (What menstrual hygiene products do you use?)",
        "হেভি ফ্লো এর সময় কতক্ষণ পরপর প্যাড পরিবর্তন করে থাকো? (How often do you change your menstrual hygiene product on heavy flow days?)",
        "লো ফ্লো এর সময় কতক্ষণ পরপর প্যাড পরিবর্তন করে থাকো? (How often do you change your menstrual hygiene product on light flow days?)",
        "কেন তুমি প্যাড ব্যবহার পছন্দ কর? (Why do you prefer using pads?)",
        "প্যাড ব্যবহার করতে গিয়ে তুমি কী ধরণের সমস্যার সম্মুখীন হয়েছো? (Do you face any issues while using pads?)",
        "প্যাড ব্যবহার করার পর এগুলোকে কী করো? (How do you dispose of used pads?)",
        "তুমি কেন প্যাড ব্যবহার করো না? (If you do not use pads, what are your reasons?)",
        "কীভাবে তুমি মাসিক স্বাস্থ্যবিধি সম্পর্কে জেনেছো? (How did you learn about menstrual hygiene products?)",
        "তোমার কাছে কি মাসিক স্বাস্থ্যবিধি সম্পর্কে যথেষ্ট তথ্য আছে বলে তুমি মনে করো? (Do you feel you have enough information about menstrual hygiene and product options?)",
        "তোমার এলাকায় কী প্যাড সহজে পাওয়া যায়? (Are menstrual hygiene products easily accessible in your area?)",
        "তোমার কী প্যাড কেনার সামর্থ্য আছে বা প্যাড কিনতে সক্ষম? (Are menstrual hygiene products affordable for you?)",
        "মাসিক স্বাস্থ্যবিধি সংক্রান্ত দ্রব্যাদি ক্রয়ের সময় তুমি কি কখনো কোনো সমস্যার সম্মুখীন হয়েছো? (Have you ever faced any challenges in accessing menstrual hygiene products?)",
        "তুমি কি মাসিকের সময় কোনো সামাজিক রীতি অনুশীলন করে থাকো? (Do you follow any cultural or traditional practices during menstruation?)",
        "তুমি কি মনে করো এটি তোমার সমাজে একটি কুসংস্কার হিসেবে গণ্য করা হয়? (Do you feel there is stigma or taboo associated with menstruation in your community?)",
        "এ বিষয়ে তুমি অন্যদের সাথে আলোচনা করতে কেমন বোধ কর? (How comfortable are you discussing menstruation openly with others?)"
    )


    private val options = listOf(
        listOf("প্যাড (Pads)", "কাপড়ের প্যাড (Cloth Pads)", "অন্যান্য (Others)"), // for Question 1
        listOf("প্রতি ১-২ ঘন্টা অন্তর (Every 1-2 Hours)", "প্রতি ৩-৪ ঘন্টা অন্তর (Every 3-5 Hours)", "৬ ঘন্টার চেয়ে বেশি (More than 6 Hours)"), // for Question 2
        listOf("প্রতি ১-২ ঘন্টা অন্তর (Every 1-2 Hours)", "প্রতি ৩-৪ ঘন্টা অন্তর (Every 3-5 Hours)", "৬ ঘন্টার চেয়ে বেশি (More than 6 Hours)"),  // for Question 3
        listOf("আরাম (Comfort)", "সহজে ব্যবহার করা যায় (Easy to use)", "সহজলভ্যতা (Availability)", "ঐতিহ্যগত কারণ (Cultural Reason)", "স্বাস্থ্যের জন্য ভালো তাই (Good for health)", "অন্যান্য (Others)"), // for Question 4
        listOf("র‍্যাশ ও চুলকানি (Rashes and Irritation)", "লিক করা (Leakage)", "আরামদায়ক নয় (Discomfort)", "পারিপার্শ্বিক পরিস্থিতি (Environmental Concern)"), // for Question 5
        listOf("সাধারণ ডাস্টবিনে ফেলে দিই (Throw in regular trash)", "টয়লেটে ফেলে দিই (Flush down in the toilet)", "মাটিতে পুঁতে ফেলি (Bury in the ground)", "অন্যান্য (Others)"), // for Question 6
        listOf("অস্বস্তিকর (Discomfort)", "অ্যালার্জির সমস্যা হয় (Allergy Issues)", "পারিপার্শ্বিক পরিস্থিতি (Environmental Concern)", "দাম (Cost)", "ঐতিহ্যগত কারণ (Cultural Reasons)", "পাওয়া যায় না (Not Available)", "অন্যান্য (Others)"), // for Question 7
        listOf("পরিবার (Family)", "বন্ধুবান্ধব (Friends)", "স্কুল (School)", "ইন্টারনেট (Internet)", "স্বাস্থ্যকর্মী (Healthcare Worker)", "খবর/বিজ্ঞাপন (News/Advertisement)", "অন্যান্য (Others)"), // for Question 8
        listOf("হ্যাঁ (Yes)", "না (No)", "জানি না (Don’t know)"), // for Question 9
        listOf("হ্যাঁ (Yes)", "না (No)", "কখনো কখনো (Sometimes)"), // for Question 10
        listOf("হ্যাঁ (Yes)", "না (No)"), // for Question 11
        listOf("হ্যাঁ (Yes)", "না (No)", "কখনো কখনো (Sometimes)"), // for Question 12
        listOf("হ্যাঁ (Yes)", "না (No)"), // for Question 13
        listOf("হ্যাঁ (Yes)", "না (No)", "কখনো কখনো (Sometimes)"), // for Question 14
        listOf("অনেক স্বাচ্ছন্দ বোধ করি (Very Comfortable)", "মোটামুটি স্বাচ্ছন্দ বোধ করি (Somewhat Comfortable)", "স্বাচ্ছন্দ বোধ করি না (Not Comfortable)", "শুধু বন্ধুবান্ধবদের সাথে আলোচনায় স্বাচ্ছন্দ বোধ করি (Only comfortable with friends)") // for Question 15
    )

    private var currentQuestionIndex = 0
    private val responses = JSONObject()
    private lateinit var nextButton: Button
    private lateinit var submitButton: Button
    private lateinit var totalStudentsEditText: EditText
    private lateinit var groupSurveyLayout: LinearLayout
    private val optionEditTexts = mutableListOf<EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_group_survey)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        totalStudentsEditText = findViewById(R.id.totalStudentsEditText)
        groupSurveyLayout = findViewById(R.id.groupSurveyLayout)

        nextButton = Button(this).apply { text = "Next" }
        submitButton = Button(this).apply { text = "Submit" }

        totalStudentsEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = validateInputs()
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        loadQuestion()

        nextButton.setOnClickListener {
            saveCurrentQuestionResponses()
            currentQuestionIndex++
            loadQuestion()
        }

        submitButton.setOnClickListener {
            saveCurrentQuestionResponses()
            saveSurveyResponses()
            Toast.makeText(this, "Survey submitted successfully!", Toast.LENGTH_SHORT).show()
            finish() // End survey
        }
    }

    private fun loadQuestion() {
        val questionText = findViewById<TextView>(R.id.questionText)

        // Set the question text
        questionText.text = "Q${currentQuestionIndex + 1}: ${questions[currentQuestionIndex]}"

        // Remove dynamically added views (options and buttons)
        groupSurveyLayout.removeAllViews()
        optionEditTexts.clear()

        // Add total students field and question text back to layout
        groupSurveyLayout.addView(totalStudentsEditText)
        groupSurveyLayout.addView(questionText)

        // Add options with EditText fields for each option
        options[currentQuestionIndex].forEach { optionText ->
            val optionLabel = TextView(this).apply {
                text = optionText
                textSize = 16f
                setPadding(0, 8, 0, 8)
            }

            val optionEditText = EditText(this).apply {
                hint = "Number of students"
                inputType = android.text.InputType.TYPE_CLASS_NUMBER
                tag = optionText
                setText("0")

                // Add TextWatcher to each option EditText
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) = validateInputs()
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                })
            }

            optionEditTexts.add(optionEditText)
            groupSurveyLayout.addView(optionLabel)
            groupSurveyLayout.addView(optionEditText)
        }

        // Add the appropriate button at the bottom
        if (currentQuestionIndex == questions.size - 1) {
            groupSurveyLayout.addView(submitButton)
        } else {
            groupSurveyLayout.addView(nextButton)
        }

        // Initial validation to set button state
        validateInputs()
    }

    private fun validateInputs() {
        val totalStudents = totalStudentsEditText.text.toString().toIntOrNull() ?: 0

        // Check if any option field is empty
        val allFieldsFilled = optionEditTexts.all { it.text.toString().isNotEmpty() }

        // Calculate the sum of all option values
        val optionsSum = optionEditTexts.sumOf { it.text.toString().toIntOrNull() ?: 0 }

        // Enable Next/Submit button only if all fields are filled and the sum matches the total students
        val isInputValid = allFieldsFilled && optionsSum == totalStudents
        nextButton.isEnabled = isInputValid
        submitButton.isEnabled = isInputValid
    }

    private fun saveCurrentQuestionResponses() {
        val responsesForQuestion = JSONObject()

        options[currentQuestionIndex].forEachIndexed { index, optionText ->
            val response = optionEditTexts[index].text.toString()
            responsesForQuestion.put(optionText, response)
        }

        responses.put(questions[currentQuestionIndex], responsesForQuestion)
    }

    private fun saveSurveyResponses() {
        val totalStudents = findViewById<EditText>(R.id.totalStudentsEditText).text.toString()
        responses.put("Total Students", totalStudents)

        // Save responses as JSON
        val fileName = "group_survey_${System.currentTimeMillis()}.json"
        val file = File(filesDir, fileName)
        file.writeText(responses.toString())
    }
}