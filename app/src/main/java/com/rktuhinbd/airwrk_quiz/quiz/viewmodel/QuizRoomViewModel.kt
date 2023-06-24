package com.rktuhinbd.airwrk_quiz.quiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rktuhinbd.airwrk_quiz.db.QuizRoomDao
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuizRoomViewModel @Inject constructor(
    private val quizRoomDao: QuizRoomDao
) : ViewModel() {

    val quizDataObserver: LiveData<List<QuizData>> = quizRoomDao.getQuizList()

    fun addQuizData(data: QuizData) {
        viewModelScope.launch {
            quizRoomDao.insertQuiz(data)
        }
    }

    fun getQuizDataByDate(date: String) {
        viewModelScope.launch {
            quizRoomDao.getQuizByDate(date)
        }
    }

}