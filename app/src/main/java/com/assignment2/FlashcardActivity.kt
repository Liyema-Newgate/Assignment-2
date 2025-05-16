package com.assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// FlashcardActivity.kt - Question Screen
class FlashcardActivity : AppCompatActivity() {
    private lateinit var questions: Array<String>
    private lateinit var answers: BooleanArray
    private var currentIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)

        // Initialize questions and answers
        questions = arrayOf(
            "Nelson Mandela was the president in 1994",
            "World War II ended in 1945",
            "The Titanic sank in 1912",
            "Python was created in 2000",
            "The Moon landing occurred in 1969"
        )
        answers = booleanArrayOf(true, true, true, false, true)

        val tvQuestion = findViewById<TextView>(R.id.tv_question)
        val btnTrue = findViewById<Button>(R.id.btn_true)
        val btnFalse = findViewById<Button>(R.id.btn_false)
        val btnNext = findViewById<Button>(R.id.btn_next)
        val tvFeedback = findViewById<TextView>(R.id.tv_feedback)

        fun updateQuestion() {
            tvQuestion.text = questions[currentIndex]
            btnNext.isEnabled = false
            btnTrue.isEnabled = true
            btnFalse.isEnabled = true
            tvFeedback.text = ""
        }

        fun checkAnswer(userAnswer: Boolean) {
            val correct = answers[currentIndex]
            if (userAnswer == correct) {
                score++
                tvFeedback.text = "Correct!"
            } else {
                tvFeedback.text = "Incorrect!"
            }
            btnTrue.isEnabled = false
            btnFalse.isEnabled = false
            btnNext.isEnabled = true
        }

        btnTrue.setOnClickListener { checkAnswer(true) }
        btnFalse.setOnClickListener { checkAnswer(false) }

        btnNext.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                updateQuestion()
            } else {
                Intent(this, ScoreActivity::class.java).apply {
                    putExtra("SCORE", score)
                    putStringArrayListExtra("QUESTIONS", ArrayList(questions.toList()))
                    putExtra("ANSWERS", answers)
                    startActivity(this)
                }
                finish()
            }
        }

        updateQuestion()
    }
}