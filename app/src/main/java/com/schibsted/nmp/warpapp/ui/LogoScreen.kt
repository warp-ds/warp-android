package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.Brand
import com.schibsted.nmp.warp.components.WarpLogo
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun LogoScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Logos", onUp = onUp) {
        LogoScreenContent()
    }
}

@Composable
fun LogoScreenContent() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        //Autovex
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.Autovex)
            WarpLogo(brand = Brand.Autovex, small = true)
        }
        //Bilbasen
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.Bilbasen)
            WarpLogo(brand = Brand.Bilbasen, small = true)
        }
        //Blocket
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.Blocket)
            WarpLogo(brand = Brand.Blocket, small = true)
        }
        //DBA
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.DBA)
            WarpLogo(brand = Brand.DBA, small = true)
        }
        //Finn
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.Finn)
            WarpLogo(brand = Brand.Finn, small = true)
        }
        //Finn tagline
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.FinnTagline)
        }
        //Nettbil
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.Nettbil)
            WarpLogo(brand = Brand.Nettbil, small = true)
        }
        //Oikotie
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.Oikotie)
            WarpLogo(brand = Brand.Oikotie, small = true)
        }
        //Qasa
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.Qasa)
            WarpLogo(brand = Brand.Qasa, small = true)
        }
        //Tori
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.Tori)
            WarpLogo(brand = Brand.Tori, small = true)
        }
        //Vend
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.Vend)
            WarpLogo(brand = Brand.Vend, small = true)
        }
        //Vend Pro
        Row(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpLogo(brand = Brand.VendPro)
        }
    }
}