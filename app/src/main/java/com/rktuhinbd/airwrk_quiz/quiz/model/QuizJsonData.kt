package com.rktuhinbd.airwrk_quiz.quiz.model

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuizJsonData (

    @SerializedName("id"                ) var id               : Int?              = null,
    @SerializedName("category"          ) var category         : String?           = null,
    @SerializedName("type"              ) var type             : String?           = null,
    @SerializedName("difficulty"        ) var difficulty       : String?           = null,
    @SerializedName("question"          ) var question         : String?           = null,
    @SerializedName("correct_answer"    ) var correctAnswer    : String?           = null,
    @SerializedName("given_answer"      ) var givenAnswer      : String?           = "",
    @SerializedName("answers"           ) var answers          : ArrayList<String> = arrayListOf(),

) : Parcelable