package com.example.hilt_study_sample.di

import com.example.hilt_study_sample.data.datasource.NoticeDataSource
import com.example.hilt_study_sample.data.datasource.NoticeDataSourceImpl
import com.example.hilt_study_sample.data.service.NoticeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideNoticeRepository(noticeService: NoticeService): NoticeDataSource {
        return NoticeDataSourceImpl(noticeService = noticeService)
    }
}