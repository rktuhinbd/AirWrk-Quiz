package com.rktuhinbd.airwrk_quiz.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rktuhinbd.airwrk_quiz.R
import com.rktuhinbd.airwrk_quiz.databinding.RvItemAnswersBinding

class OptionsRvAdapter(
    private val context: Context,
    private val correctAnswer: String,
    private val givenAnswer: String,
    private val dataList: MutableList<String>
) :
    RecyclerView.Adapter<OptionsRvAdapter.ViewHolder>() {

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


        if (givenAnswer == dataSet && givenAnswer == correctAnswer) {

            viewHolder.binding.ivAnswerIndicator.visibility = View.VISIBLE

            viewHolder.binding.ivCheckIndicator.setImageDrawable(context.getDrawable(R.drawable.ic_radio_checked))
            viewHolder.binding.body.setBackgroundResource(R.drawable.shape_correct_answer)
            viewHolder.binding.ivAnswerIndicator.setImageResource(R.drawable.ic_tick_circular)
            viewHolder.binding.tvOptionTV.setTextColor(context.getColor(R.color.green_500))
        } else {
            if (givenAnswer == dataSet) {

                viewHolder.binding.ivAnswerIndicator.visibility = View.VISIBLE

                viewHolder.binding.ivCheckIndicator.setImageDrawable(context.getDrawable(R.drawable.ic_radio_checked))

                viewHolder.binding.body.setBackgroundResource(R.drawable.shape_wrong_answer)
                viewHolder.binding.ivAnswerIndicator.setImageResource(R.drawable.ic_cross_circular)
                viewHolder.binding.tvOptionTV.setTextColor(context.getColor(R.color.red_500))
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}