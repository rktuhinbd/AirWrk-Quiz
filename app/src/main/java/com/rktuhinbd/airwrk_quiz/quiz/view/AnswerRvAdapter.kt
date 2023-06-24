package com.rktuhinbd.airwrk_quiz.quiz.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rktuhinbd.airwrk_quiz.R
import com.rktuhinbd.airwrk_quiz.databinding.RvItemAnswersBinding

class AnswerRvAdapter(
    private val context: Context,
    answer: String,
    private val dataList: MutableList<String>
) :
    RecyclerView.Adapter<AnswerRvAdapter.ViewHolder>() {

    var correctAnswer = answer

    var onItemClick: ((String) -> Unit)? = null

    class ViewHolder(val binding: RvItemAnswersBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = RvItemAnswersBinding.inflate(inflater)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val dataSet = dataList[position]

        viewHolder.binding.ivCheckIndicator.setImageDrawable(context.getDrawable(R.drawable.ic_radio_unchecked))

        viewHolder.binding.body.setBackgroundResource(R.drawable.shape_quiz_option)

        viewHolder.binding.ivAnswerIndicator.visibility = View.GONE

        viewHolder.binding.tvOptionTV.setTextColor(context.getColor(R.color.text_color_600))

        viewHolder.binding.tvOptionTV.text = dataSet

        viewHolder.binding.body.setOnClickListener {

            viewHolder.binding.ivCheckIndicator.setImageDrawable(context.getDrawable(R.drawable.ic_radio_checked))

            if (correctAnswer == dataSet) {
                viewHolder.binding.body.setBackgroundResource(R.drawable.shape_correct_answer)
                viewHolder.binding.ivAnswerIndicator.setImageResource(R.drawable.ic_correct_answer)
                viewHolder.binding.tvOptionTV.setTextColor(context.getColor(R.color.green_500))
            } else {
                viewHolder.binding.body.setBackgroundResource(R.drawable.shape_wrong_answer)
                viewHolder.binding.ivAnswerIndicator.setImageResource(R.drawable.ic_wrong_answer)
                viewHolder.binding.tvOptionTV.setTextColor(context.getColor(R.color.red))
            }

            viewHolder.binding.ivAnswerIndicator.visibility = View.VISIBLE

            onItemClick?.invoke(dataSet)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(answer: String, data: MutableList<String>) {

        correctAnswer = answer

        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}