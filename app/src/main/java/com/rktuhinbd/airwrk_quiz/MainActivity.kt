package com.rktuhinbd.airwrk_quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.airwrk_quiz.databinding.ActivityMainBinding
import com.rktuhinbd.airwrk_quiz.history.HistoryActivity
import com.rktuhinbd.airwrk_quiz.quiz.view.QuizActivity
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startQuizBtn.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        binding.viewHistoryBtn.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}