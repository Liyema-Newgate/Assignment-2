package com.assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// ScoreActivity.kt - Results Screen
class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("SCORE", 0)
        val questions = intent.getStringArrayListExtra("QUESTIONS")
        val answers = intent.getBooleanArrayExtra("ANSWERS")

        findViewById<TextView>(R.id.tv_score).text = "Score: $score/5"
        findViewById<TextView>(R.id.tv_feedback).text =
            if (score >= 3) "Great job!" else "Keep practising!"

        findViewById<Button>(R.id.btn_review).setOnClickListener {
            Intent(this, ReviewActivity::class.java).apply {
                putStringArrayListExtra("QUESTIONS", questions)
                putExtra("ANSWERS", answers)
                startActivity(this)
            }
        }

        findViewById<Button>(R.id.btn_exit).setOnClickListener {
            finishAffinity()
        }
    }
}