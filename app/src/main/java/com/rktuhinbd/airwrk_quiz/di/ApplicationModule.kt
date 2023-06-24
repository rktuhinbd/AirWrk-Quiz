package com.rktuhinbd.airwrk_quiz.di

import android.content.Context
import com.rktuhinbd.airwrk_quiz.db.AppDB
import com.rktuhinbd.airwrk_quiz.db.QuizRoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun providesQuizRoomDBService(@ApplicationContext context: Context): QuizRoomDao {
        return AppDB.getDatabase(context).quizRoomDao()
    }

}