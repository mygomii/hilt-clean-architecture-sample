package com.example.hilt_study_sample.di

import com.example.hilt_study_sample.data.service.NoticeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideNoticeService(httpClient: HttpClient): NoticeService {
        return NoticeService(httpClient = httpClient)
    }
}