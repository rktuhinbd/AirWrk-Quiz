package com.rktuhinbd.airwrk_quiz.quizresult

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import com.rktuhinbd.airwrk_quiz.databinding.ActivityQuizResultBinding
import com.rktuhinbd.airwrk_quiz.quiz.viewmodel.QuizRoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizResultBinding

    private lateinit var roomViewModel: QuizRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomViewModel = ViewModelProvider(this)[QuizRoomViewModel::class.java]

        initObserver()
    }

    private fun initObserver() {

        roomViewModel.quizDataObserver.observe(this@QuizResultActivity) { data ->
            if (data.isNotEmpty()) {
                Log.d("WOW", "onCreate: ${GsonBuilder().setPrettyPrinting().create().toJson(data)}")
            }
        }
    }
}