package com.rktuhinbd.airwrk_quiz.quiz.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = QuizTable.TABLE_NAME)
@TypeConverters(QuizJsonDataTypeConverter::class)
@Parcelize
data class QuizData(

    @PrimaryKey(autoGenerate = true)
    @SerializedName(QuizTable.ID)
    val id: Int = 1,

    @ColumnInfo(name = QuizTable.DATE)
    @SerializedName(QuizTable.DATE)
    var date: String? = null,

    @ColumnInfo(name = QuizTable.TIME_TAKEN)
    @SerializedName(QuizTable.TIME_TAKEN)
    var timeTaken: Int? = 0,

    @ColumnInfo(name = QuizTable.QUIZ_DATA)
    @SerializedName(QuizTable.QUIZ_DATA)
    var quizData: MutableList<QuizJsonData>? = arrayListOf()

) : Parcelable
