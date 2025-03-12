package com.example.hilt_study_sample.domain

import com.example.hilt_study_sample.data.model.Notice
import com.example.hilt_study_sample.data.repository.NoticeRepository

class GetNoticesUseCase(
    private val noticeRepository: NoticeRepository
) {
    suspend operator fun invoke(): List<Notice> = noticeRepository.getNotices()

}