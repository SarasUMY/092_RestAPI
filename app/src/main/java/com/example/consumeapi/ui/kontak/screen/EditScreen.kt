package com.example.consumeapi.ui.kontak.screen

import DestinasiNavigasi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.consumeapi.ui.kontak.viewmodel.EditViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.consumeapi.DestinasiHome
import com.example.consumeapi.ui.PenyediaViewModel
import com.example.consumeapi.ui.theme.TopAppBarKontak
import kotlinx.coroutines.launch

object EditDestination : DestinasiNavigasi {
    override val route =  "edit" //memberi nama setiap halaman
    override val titleRes = "Edit Kontak" //memberi caption topbar
    const val kontakId = "itemId" //menampung parameter id yang dikirim lewat service
    val routeWithArgs = "$route/{$kontakId}" //rute yang membawa argumen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBarKontak(
                title = DestinasiHome.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryKontakBody(
            insertUiState = viewModel.editKontakState,
            onSiswaValueChange = viewModel::updateInsertKontakState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateKontak()
                    onNavigateUp
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}