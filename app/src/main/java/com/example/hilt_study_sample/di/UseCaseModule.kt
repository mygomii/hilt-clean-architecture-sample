package com.example.hilt_study_sample.di

import com.example.hilt_study_sample.data.repository.NoticeRepository
import com.example.hilt_study_sample.domain.GetNoticesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideNoticeUseCase(noticeRepository: NoticeRepository): GetNoticesUseCase {
        return GetNoticesUseCase(noticeRepository = noticeRepository)
    }
}
