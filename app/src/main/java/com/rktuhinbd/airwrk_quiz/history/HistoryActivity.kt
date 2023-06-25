package com.rktuhinbd.airwrk_quiz.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rktuhinbd.airwrk_quiz.MainActivity
import com.rktuhinbd.airwrk_quiz.R
import com.rktuhinbd.airwrk_quiz.databinding.ActivityHistoryBinding
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizData
import com.rktuhinbd.airwrk_quiz.quiz.viewmodel.QuizRoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    private lateinit var roomViewModel: QuizRoomViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomViewModel = ViewModelProvider(this)[QuizRoomViewModel::class.java]

        initComponent()
        initListener()
        initObserver()
    }

    private fun initListener() {
        binding.toolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initComponent() {
        binding.toolbar.tvTitle.text = getString(R.string.quiz_history)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@HistoryActivity, MainActivity::class.java))
        finish()
    }

    private fun initObserver() {

        roomViewModel.quizDataObserver.observe(this@HistoryActivity) { data ->
            if (data.isNotEmpty()) {
                showQuizHistory(data)
            }
        }
    }

    private fun showQuizHistory(data: List<QuizData>?) {

        val rvAdapter = QuizHistoryRvAdapter(this, data as MutableList<QuizData>)
        binding.rvRetake.adapter = rvAdapter

        rvAdapter.onItemClick = {
            val intent = Intent(this@HistoryActivity, QuizDetailsActivity::class.java)
            intent.putParcelableArrayListExtra("data_extra", ArrayList(it))
            startActivity(intent)
        }
    }
}