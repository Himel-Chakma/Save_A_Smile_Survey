package com.example.saveasmilesurvey

import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import org.json.JSONObject
import java.io.File
import java.util.Date

class ActivitySurvey : AppCompatActivity() {
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

    // Declare views here without initializing them
    private lateinit var questionNumberText: TextView
    private lateinit var questionText: TextView
    private lateinit var optionGroup: RadioGroup
    private lateinit var nextButton: Button
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton
    private lateinit var option5: RadioButton
    private lateinit var option6: RadioButton
    private lateinit var option7: RadioButton

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
        option5 = findViewById(R.id.option5)
        option6 = findViewById(R.id.option6)
        option7 = findViewById(R.id.option7)

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
        option5.text = currentOptions.getOrNull(4)
        option6.text = currentOptions.getOrNull(5)
        option7.text = currentOptions.getOrNull(6)

        option1.isVisible = option1.text.isNotEmpty()
        option2.isVisible = option2.text.isNotEmpty()
        option3.isVisible = option3.text.isNotEmpty()
        option4.isVisible = option4.text.isNotEmpty()
        option5.isVisible = option5.text.isNotEmpty()
        option6.isVisible = option6.text.isNotEmpty()
        option7.isVisible = option7.text.isNotEmpty()

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

        Toast.makeText(this, "Survey Saved successfully!", Toast.LENGTH_SHORT).show()
    }
}
