package com.rktuhinbd.airwrk_quiz.quizresult

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import com.rktuhinbd.airwrk_quiz.R
import com.rktuhinbd.airwrk_quiz.databinding.ActivityQuizResultBinding
import com.rktuhinbd.airwrk_quiz.history.HistoryActivity
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizData
import com.rktuhinbd.airwrk_quiz.quiz.view.QuizActivity
import com.rktuhinbd.airwrk_quiz.quiz.viewmodel.QuizRoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizResultBinding

    private lateinit var roomViewModel: QuizRoomViewModel

    private var quizDataList: ArrayList<QuizData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomViewModel = ViewModelProvider(this)[QuizRoomViewModel::class.java]

        initListener()
        initObserver()
    }

    private fun initListener() {

        binding.retryBtn.setOnClickListener {
            startActivity(Intent(this@QuizResultActivity, QuizActivity::class.java))
            finish()
        }

        binding.viewHistoryBtn.setOnClickListener {
            startActivity(Intent(this@QuizResultActivity, HistoryActivity::class.java))
            finish()
        }
    }

    private fun initObserver() {

        roomViewModel.quizDataObserver.observe(this@QuizResultActivity) { data ->
            if (data.isNotEmpty()) {
                quizDataList.clear()
                quizDataList.addAll(data)

                showQuizResult(quizDataList[quizDataList.size - 1])
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showQuizResult(quizData: QuizData) {

        val congratulations: String
        val passFail: String
        var score: Int
        var correctAnswerCount = 0
        var wrongAnswerCount = 0
        var skippedCount = 0

        quizData.quizData?.forEach {
            if (it.correctAnswer == it.givenAnswer) {
                correctAnswerCount++
            } else {
                if (it.givenAnswer.equals("")) {
                    skippedCount++
                }
                wrongAnswerCount++
            }
        }

        score = correctAnswerCount - (wrongAnswerCount + skippedCount)
        if (score < 0) {
            score = 0
        }

        val timeTaken: Int = quizData.timeTaken ?: 0

        if (score >= (quizData.quizData?.size?.div(2)!!)) {
            binding.layoutExamResult.ivLogo.setImageResource(R.drawable.ic_passed)
            congratulations = getString(R.string.congratulation_)
            passFail = getString(R.string.you_are_passed)
        } else {
            binding.layoutExamResult.ivLogo.setImageResource(R.drawable.ic_failed)
            congratulations = getString(R.string.sorry_)
            passFail = getString(R.string.you_are_failed)
        }

        binding.layoutExamResult.tvCongratulations.text = congratulations
        binding.layoutExamResult.tvPassFailInfo.text = passFail
        binding.layoutExamResult.tvScore.text = score.toString()
        binding.layoutExamResult.tvCorrectAnswers.text = correctAnswerCount.toString()
        binding.layoutExamResult.tvWrongAnswer.text = wrongAnswerCount.toString()
        binding.layoutExamResult.tvNotAnswered.text = skippedCount.toString()
        binding.layoutExamResult.tvNegativeMarking.text = "${wrongAnswerCount + skippedCount}"

        val time: String = if (timeTaken > 59) {
            "${timeTaken / 60} : ${timeTaken % 60} Minute"
        } else {
            "$timeTaken Seconds"
        }
        binding.layoutExamResult.tvTime.text = time

    }
}