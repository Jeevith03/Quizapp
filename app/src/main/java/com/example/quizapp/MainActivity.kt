package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var textViewQuestion: TextView
    private lateinit var radioGroupOptions: RadioGroup
    private lateinit var radioButtonOption1: RadioButton
    private lateinit var radioButtonOption2: RadioButton
    private lateinit var radioButtonOption3: RadioButton
    private lateinit var radioButtonOption4: RadioButton
    private lateinit var buttonNext: Button

    private val questions = arrayOf(
        "Question 1?",
        "Question 2?",
        "Question 3?"
    )

    private val options = arrayOf(
        arrayOf("Option 1A", "Option 1B", "Option 1C", "Option 1D"),
        arrayOf("Option 2A", "Option 2B", "Option 2C", "Option 2D"),
        arrayOf("Option 3A", "Option 3B", "Option 3C", "Option 3D")
    )

    private val correctAnswers = intArrayOf(0, 1, 2)
    private var currentQuestion = 0
    private var selectedAnswer = -1
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewQuestion = findViewById(R.id.textViewQuestion)
        radioGroupOptions = findViewById(R.id.radioGroupOptions)
        radioButtonOption1 = findViewById(R.id.radioButtonOption1)
        radioButtonOption2 = findViewById(R.id.radioButtonOption2)
        radioButtonOption3 = findViewById(R.id.radioButtonOption3)
        radioButtonOption4 = findViewById(R.id.radioButtonOption4)
        buttonNext = findViewById(R.id.buttonNext)

        displayQuestion()

        buttonNext.setOnClickListener {
            if (selectedAnswer != -1) {
                checkAnswer()
                if (currentQuestion < questions.size - 1) {
                    currentQuestion++
                    selectedAnswer = -1
                    displayQuestion()
                } else {
                    showResult()
                }
            }
        }

        radioGroupOptions.setOnCheckedChangeListener { _, checkedId ->
            selectedAnswer = when (checkedId) {
                R.id.radioButtonOption1 -> 0
                R.id.radioButtonOption2 -> 1
                R.id.radioButtonOption3 -> 2
                R.id.radioButtonOption4 -> 3
                else -> -1
            }
        }
    }

    private fun displayQuestion() {
        textViewQuestion.text = questions[currentQuestion]
        radioButtonOption1.text = options[currentQuestion][0]
        radioButtonOption2.text = options[currentQuestion][1]
        radioButtonOption3.text = options[currentQuestion][2]
        radioButtonOption4.text = options[currentQuestion][3]
        radioGroupOptions.clearCheck()
    }

    private fun checkAnswer() {
        if (selectedAnswer == correctAnswers[currentQuestion]) {
            score++
        }
    }

    private fun showResult() {
        val resultText = "Result: $score out of ${questions.size}"
        textViewQuestion.text = resultText
        radioGroupOptions.visibility = RadioGroup.INVISIBLE
        buttonNext.visibility = Button.INVISIBLE
    }
}