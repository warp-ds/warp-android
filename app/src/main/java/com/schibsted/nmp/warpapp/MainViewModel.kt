package com.schibsted.nmp.warpapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _flavor: MutableStateFlow<String> = MutableStateFlow("tori")
    val flavor: StateFlow<String> = _flavor.asStateFlow()

    fun setFlavor(flavor: String) {
        android.util.Log.d("perstest", "set flavor: $flavor")
        _flavor.value = flavor
    }
}