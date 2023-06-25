package com.rktuhinbd.airwrk_quiz.history

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.rktuhinbd.airwrk_quiz.R
import com.rktuhinbd.airwrk_quiz.databinding.ActivityQuizDetailsBinding
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizJsonData

class QuizDetailsActivity : AppCompatActivity() {

    // * * * * * View Properties * * * * * //

    private lateinit var binding: ActivityQuizDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.tvTitle.text = getString(R.string.quiz_details)

        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }

        val data = intent.getParcelableArrayListExtra<QuizJsonData>("data_extra")

        if (data != null) {
            val rvAdapter = QuestionRvAdapter(this, data)
            binding.quizDetailsRV.adapter = rvAdapter
        }
    }
}
