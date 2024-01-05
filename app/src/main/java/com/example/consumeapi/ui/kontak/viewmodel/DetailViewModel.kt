package com.example.consumeapi.ui.kontak.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumeapi.model.Kontak
import com.example.consumeapi.repository.KontakRepository
import com.example.consumeapi.ui.kontak.screen.DetailDestination
import kotlinx.coroutines.launch

sealed class DetailKontakUiState { //kelas tertutup untuk mengelola status(state) kondisi success atau error, digunakan ketka remote databasenya diluar/bukan roomdatabase yang databasenya local
    data class Success(
        val kontak: Kontak) : DetailKontakUiState()

    object Error : DetailKontakUiState()

    object Loading : DetailKontakUiState()
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val kontakRepository: KontakRepository
) : ViewModel() {
    private val kontakId: Int = checkNotNull(savedStateHandle[DetailDestination.kontakId])

    var detailsKontakUiState: DetailKontakUiState by mutableStateOf(DetailKontakUiState.Loading)
        private set

    init {
        getKontakByID()
    }

    fun getKontakByID() {
        viewModelScope.launch {
            detailsKontakUiState = DetailKontakUiState.Loading
            detailsKontakUiState = try {
                DetailKontakUiState.Success(
                    kontak = kontakRepository.getKontakById(kontakId)
                )
            } catch (e: Exception) {
                DetailKontakUiState.Error
            }
        }
    }
}