package com.rktuhinbd.airwrk_quiz.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizData
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizTable

@Dao
interface QuizRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuiz(data: QuizData)

    @Query("SELECT * FROM ${QuizTable.TABLE_NAME} WHERE ${QuizTable.DATE} = :date")
    fun getQuizByDate(date: String): QuizData?

    @Query("SELECT * FROM ${QuizTable.TABLE_NAME}")
    fun getQuizList(): LiveData<List<QuizData>>

}