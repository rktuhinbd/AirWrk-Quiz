package com.rktuhinbd.airwrk_quiz.utilities

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizJsonData
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {

    fun readJsonFile(context: Context, fileName: String): ArrayList<QuizJsonData> {
        var arrayList: ArrayList<QuizJsonData> = arrayListOf()

        try {
            val inputStream: InputStream = context.assets.open(fileName)

            val reader = BufferedReader(InputStreamReader(inputStream))

            val stringBuilder = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            val jsonString = stringBuilder.toString()

            val gson = Gson()
            arrayList = gson.fromJson(jsonString, object : TypeToken<ArrayList<QuizJsonData>>() {}.type)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return arrayList
    }

    fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }
}