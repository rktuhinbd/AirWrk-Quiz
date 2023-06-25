package com.rktuhinbd.airwrk_quiz.quizresult

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import com.rktuhinbd.airwrk_quiz.databinding.ActivityQuizEndBinding
import com.rktuhinbd.airwrk_quiz.quiz.viewmodel.QuizRoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizEndActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizEndBinding

    private lateinit var roomViewModel: QuizRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomViewModel = ViewModelProvider(this)[QuizRoomViewModel::class.java]

        initObserver()
    }

    private fun initObserver() {

        roomViewModel.quizDataObserver.observe(this@QuizEndActivity) { data ->
            if (data.isNotEmpty()) {
                Log.d("WOW", "onCreate: ${GsonBuilder().setPrettyPrinting().create().toJson(data)}")
            }
        }
    }
}