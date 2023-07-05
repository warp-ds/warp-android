@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warpapp.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.schibsted.nmp.warp.components.WarpTopAppBar
import com.schibsted.nmp.warpapp.MainViewModel

@Composable
internal fun DetailsScaffold(title: String, onUp: () -> Unit, content: @Composable () -> Unit) {

    val viewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity)
    var menuVisible by remember { mutableStateOf(false) }
    val flavor = viewModel.flavor.collectAsState()
    Scaffold(
        topBar = {
            WarpTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onUp
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text("$title (${flavor.value})")
                },
                actions = {
                    IconButton(onClick = { menuVisible = !menuVisible }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(expanded = menuVisible, onDismissRequest = { menuVisible = false }) {
                        DropdownMenuItem(text = { Text("Finn") }, onClick = {
                            viewModel.setFlavor("finn")
                            menuVisible = false
                        })
                        DropdownMenuItem(text = { Text("Tori") }, onClick = {
                            viewModel.setFlavor("tori")
                            menuVisible = false
                        })
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            content()
        }
    }
}
