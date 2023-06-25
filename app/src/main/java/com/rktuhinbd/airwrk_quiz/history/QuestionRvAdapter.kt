package com.rktuhinbd.airwrk_quiz.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rktuhinbd.airwrk_quiz.databinding.RvItemQuizDetailsBinding
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizJsonData

class QuestionRvAdapter(
    private val context: Context,
    private val dataList: MutableList<QuizJsonData>
) :
    RecyclerView.Adapter<QuestionRvAdapter.ViewHolder>() {

    class ViewHolder(val binding: RvItemQuizDetailsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = RvItemQuizDetailsBinding.inflate(inflater)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val dataSet = dataList[position]

        viewHolder.binding.questionTV.text = "${position + 1} : ${dataSet.question}"

        val optionsRvAdapter = OptionsRvAdapter(
            context,
            dataSet.correctAnswer ?: "",
            dataSet.givenAnswer ?: "",
            dataSet.answers
        )

        viewHolder.binding.optionsRV.adapter = optionsRvAdapter
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
