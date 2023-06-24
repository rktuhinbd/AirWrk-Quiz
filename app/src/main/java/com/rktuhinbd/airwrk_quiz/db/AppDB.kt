package com.rktuhinbd.airwrk_quiz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rktuhinbd.airwrk_quiz.quiz.model.QuizData

@Database(entities = [QuizData::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun quizRoomDao(): QuizRoomDao

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            // if the INSTANCE is not null, then return it, if it is then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "air_wrk_db"
                )
                    .allowMainThreadQueries()
                    //.addMigrations(migration)
                    .build()
                INSTANCE = instance
                // return instance
                return instance
            }
        }
    }
}