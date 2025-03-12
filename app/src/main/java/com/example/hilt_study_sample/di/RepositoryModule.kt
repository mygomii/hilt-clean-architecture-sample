package com.example.hilt_study_sample.di

import com.example.hilt_study_sample.data.datasource.NoticeDataSource
import com.example.hilt_study_sample.data.repository.NoticeRepository
import com.example.hilt_study_sample.data.repository.NoticeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNoticeRepository(noticeDataSource: NoticeDataSource): NoticeRepository {
        return NoticeRepositoryImpl(noticeDataSource = noticeDataSource)
    }
}