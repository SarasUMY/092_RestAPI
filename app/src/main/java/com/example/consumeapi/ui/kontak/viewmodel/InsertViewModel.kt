package com.example.consumeapi.ui.kontak.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.consumeapi.repository.KontakRepository

class InsertViewModel(private val kontakRepository: KontakRepository) : ViewModel() {
    var insertKontakState by mutableStateOf(InsertUiState())
        private set
}