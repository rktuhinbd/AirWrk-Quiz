package com.rktuhinbd.airwrk_quiz.quiz.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuizJsonDataTypeConverter {

    @TypeConverter
    fun fromJson(json: String): List<QuizJsonData> {
        // Convert the JSON string to a List<QuizJsonData>
        val type = object : TypeToken<List<QuizJsonData>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(data: List<QuizJsonData>): String {
        // Convert the List<QuizJsonData> to a JSON string
        return Gson().toJson(data)
    }
}