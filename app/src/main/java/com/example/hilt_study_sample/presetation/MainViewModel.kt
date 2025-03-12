package com.example.hilt_study_sample.presetation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt_study_sample.data.model.Notice
import com.example.hilt_study_sample.domain.GetNoticesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject internal constructor(
    private val getNoticesUseCase: GetNoticesUseCase
) : ViewModel() {
    private val _noticeList: MutableStateFlow<List<Notice>> = MutableStateFlow(emptyList())
    val noticeList: StateFlow<List<Notice>> = _noticeList.asStateFlow()

    init {
        viewModelScope.launch {
            _noticeList.value = getNoticesUseCase.invoke()
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}