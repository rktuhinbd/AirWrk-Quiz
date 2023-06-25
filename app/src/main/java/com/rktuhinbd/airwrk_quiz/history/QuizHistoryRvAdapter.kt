package com.rktuhinbd.airwrk_quiz.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rktuhinbd.airwrk_quiz.databinding.RvItemQuizHistoryBinding
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizData
import com.rktuhinbd.airwrk_quiz.utilities.Utils

class QuizHistoryRvAdapter(
    private val context: Context,
    private val dataList: MutableList<QuizData>
) :
    RecyclerView.Adapter<QuizHistoryRvAdapter.ViewHolder>() {

    var onItemClick: ((QuizData) -> Unit)? = null

    class ViewHolder(val binding: RvItemQuizHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = RvItemQuizHistoryBinding.inflate(inflater)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val dataSet = dataList[position]

        viewHolder.binding.tvDate.text = Utils.formatDate(dataSet.date ?: "")
        viewHolder.binding.tvTime.text = Utils.formatTime(dataSet.date ?: "")

        var score: Int
        var correctAnswerCount = 0
        var wrongAnswerCount = 0
        var skippedCount = 0

        dataSet.quizData?.forEach {
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

        viewHolder.binding.tvScore.text = "Your Score: $score/${dataSet.quizData?.size}"

        viewHolder.binding.body.setOnClickListener {
            onItemClick?.invoke(dataSet)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(data: MutableList<QuizData>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}