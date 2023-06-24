package com.rktuhinbd.airwrk_quiz.quiz.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.rktuhinbd.airwrk_quiz.R
import com.rktuhinbd.airwrk_quiz.databinding.DialogAnswerStatusBinding
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizJsonData

class AnswerStatusDialog(val data: QuizJsonData) : DialogFragment() {

    private lateinit var binding: DialogAnswerStatusBinding

    var onCallbackListener: ((Boolean) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAnswerStatusBinding.inflate(layoutInflater)

        binding.tvQuestion.text = data.question

        if (data.givenAnswer == data.correctAnswer) {
            binding.ivLogo.setImageDrawable(requireContext().getDrawable(R.drawable.logo_correct_answer))
            binding.tvAnswerStatus.text = "Your answer: ${data.givenAnswer} is correct!"
            binding.tvAnswerStatus.setTextColor(requireContext().getColor(R.color.green_500))
        } else {
            binding.ivLogo.setImageDrawable(requireContext().getDrawable(R.drawable.logo_wrong_answer))
            binding.tvAnswerStatus.setTextColor(requireContext().getColor(R.color.red))
            if (!TextUtils.isEmpty(data.givenAnswer)) {
                binding.tvAnswerStatus.text = "Your answer: ${data.givenAnswer} is wrong!!!"
            } else {
                binding.tvAnswerStatus.text = "You haven't given any answer!!!"
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {

        binding.nextBTN.setOnClickListener {
            onCallbackListener?.invoke(true)
            dismiss()
        }
    }
}