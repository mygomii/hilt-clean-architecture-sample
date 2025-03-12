package com.example.hilt_study_sample.data.repository

import com.example.hilt_study_sample.data.model.Notice

interface NoticeRepository {
    suspend fun getNotices(): List<Notice>
}