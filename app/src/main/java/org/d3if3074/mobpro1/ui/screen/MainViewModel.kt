package org.d3if3074.mobpro1.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3074.mobpro1.model.Sepatu
import org.d3if3074.mobpro1.network.SepatuApi

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Sepatu>())
        private set

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.value = SepatuApi.service.getSepatu()
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}