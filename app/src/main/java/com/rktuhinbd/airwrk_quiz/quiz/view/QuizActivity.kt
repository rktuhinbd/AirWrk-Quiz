package com.rktuhinbd.airwrk_quiz.quiz.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rktuhinbd.airwrk_quiz.R
import com.rktuhinbd.airwrk_quiz.databinding.ActivityQuizBinding
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizData
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizJsonData
import com.rktuhinbd.airwrk_quiz.quiz.viewmodel.QuizRoomViewModel
import com.rktuhinbd.airwrk_quiz.quizresult.QuizResultActivity
import com.rktuhinbd.airwrk_quiz.utilities.Utils.getCurrentDateTime
import com.rktuhinbd.airwrk_quiz.utilities.Utils.readJsonFile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {

    // * * * * * * View Properties * * * * * //

    private lateinit var binding: ActivityQuizBinding

    private lateinit var roomViewModel: QuizRoomViewModel

    private lateinit var rvAdapter: AnswerRvAdapter

    private lateinit var answerDialog: AnswerStatusDialog


    // * * * * * * Data Properties * * * * * //

    private val TAG: String = "QuizActivity"

    private var quizList: ArrayList<QuizJsonData> = arrayListOf()

    private var answerList: ArrayList<QuizJsonData> = arrayListOf()

    private val totalTimeInMillis: Long = 5000 /*** => 60 secs ***/

    private var questionIndex: Int = 0

    private var score: Int = 0

    private var totalTimeTakenInSec: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizList = readJsonFile(this, "quiz_json.json")

        initComponents()
        initQuizRecyclerView()
    }

    private fun initComponents() {

        roomViewModel = ViewModelProvider(this)[QuizRoomViewModel::class.java]

        startCountDown()

        binding.toolbar.tvQuestionIndex.text = "${questionIndex + 1}/${quizList.size}"
        binding.toolbar.tvScore.text = "Score: $score"

        binding.questionTV.text = quizList[questionIndex].question

    }

    private fun initQuizRecyclerView() {

        rvAdapter = AnswerRvAdapter(
            this,
            quizList[questionIndex].correctAnswer ?: "",
            quizList[questionIndex].answers
        )
        binding.answersRV.adapter = rvAdapter

        rvAdapter.onItemClick = {
            quizList[questionIndex].givenAnswer = it
            countdownTimer.cancel()
            showAnswerDialog()
        }
    }

    private fun startCountDown() {
        binding.toolbar.tvTimer.setTextColor(getColor(R.color.text_color_600))
        countdownTimer.start()
    }

    @SuppressLint("SetTextI18n")
    private fun showAnswerDialog() {

        answerDialog = AnswerStatusDialog(
            quizList[questionIndex]
        )
        answerDialog.isCancelable = false
        answerDialog.show(supportFragmentManager, TAG)

        answerDialog.onCallbackListener = {

            if (questionIndex + 1 < quizList.size) {

                answerList.add(quizList[questionIndex])

                if (quizList[questionIndex].givenAnswer == quizList[questionIndex].correctAnswer) {
                    score++
                } else {
                    score--
                    if (score < 0) {
                        score = 0
                    }
                }
                questionIndex++
                startCountDown()

                binding.toolbar.tvQuestionIndex.text = "${questionIndex + 1}/${quizList.size}"
                binding.toolbar.tvScore.text = "Score: $score"

                binding.questionTV.text = quizList[questionIndex].question

                rvAdapter.updateData(
                    quizList[questionIndex].correctAnswer ?: "",
                    quizList[questionIndex].answers
                )

            } else {

                if(questionIndex == quizList.size - 1){
                    answerList.add(quizList[questionIndex])

                    if (quizList[questionIndex].givenAnswer == quizList[questionIndex].correctAnswer) {
                        score++
                    } else {
                        score--
                        if (score < 0) {
                            score = 0
                        }
                    }

                    binding.toolbar.tvQuestionIndex.text = "${questionIndex + 1}/${quizList.size}"
                    binding.toolbar.tvScore.text = "Score: $score"
                }

                val quizData = QuizData()
                quizData.quizData?.addAll(answerList)
                quizData.date = getCurrentDateTime()
                quizData.timeTaken = totalTimeTakenInSec

                roomViewModel.addQuizData(quizData)

                startActivity(Intent(this, QuizResultActivity::class.java))
            }
        }
    }

    private val countdownTimer = object : CountDownTimer(totalTimeInMillis, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val secondsRemaining = millisUntilFinished / 1000

            totalTimeTakenInSec++

            if (secondsRemaining < 16) {
                binding.toolbar.tvTimer.setTextColor(getColor(R.color.red_500))
            }

            binding.toolbar.tvTimer.text = "$secondsRemaining seconds"
        }

        override fun onFinish() {
            binding.toolbar.tvTimer.text = "Time's up!"

            quizList[questionIndex].givenAnswer = ""
            showAnswerDialog()
        }
    }

    override fun finish() {
        super.finish()
        countdownTimer.cancel()
    }
}