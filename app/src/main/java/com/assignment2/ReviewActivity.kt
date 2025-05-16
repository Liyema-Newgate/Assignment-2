package com.assignment2

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView//preventing the class calls from errors
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// ReviewActivity.kt - Answer Review
class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val questions = intent.getStringArrayListExtra("QUESTIONS")
        val answers = intent.getBooleanArrayExtra("ANSWERS")
        val container = findViewById<LinearLayout>(R.id.review_container)

        questions?.forEachIndexed { i, q ->
            val tv = TextView(this).apply {
                text = "${i + 1}. $q\nCorrect answer: ${answers?.get(i)}"
                setPadding(0, 16.dp, 0, 16.dp)
            }
            container.addView(tv)
        }
    }

    private val Int.dp: Int get() = (this * resources.displayMetrics.density).toInt()
}