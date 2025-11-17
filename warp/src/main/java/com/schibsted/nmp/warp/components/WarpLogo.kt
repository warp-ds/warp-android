package com.schibsted.nmp.warp.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.WarpBrandIconResource
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpResources.icons

/**
 * Logo component for the different brands.
 * @param modifier Modifier to be applied to the logo.
 * @param small If true, the logo will be icon sized 32.dp x 32.dp
 * @param brand The brand to be displayed.
 */
@Composable
fun WarpLogo(
    modifier: Modifier = Modifier,
    small: Boolean = false,
    brand: String
) {
    val brandIcon = getIconForBrand(brand, small)
    brandIcon?.let {
        LogoView(modifier, it)
    }
}

@Composable
fun WarpLogo(
    modifier: Modifier = Modifier,
    small: Boolean = false,
    brand: Brand
) {
    WarpLogo(modifier, small, brand.identifier)
}

@Composable
private fun LogoView(
    modifier: Modifier,
    icon: WarpIconResource,

    ) {
    Icon(
        imageVector = icon.vector,
        contentDescription = icon.description,
        tint = Color.Unspecified,
        modifier = modifier
    )
}

@Composable
private fun getIconForBrand(brandName: String, small: Boolean): WarpBrandIconResource? {
    val brand = Brand.entries.find { it.identifier == brandName }
    return when (brand) {
        Brand.Autovex -> if (small) icons.autovex else icons.autovexLarge
        Brand.Bilbasen -> if (small) icons.bilbasen else icons.bilbasenLarge
        Brand.Blocket -> if (small) icons.blocket else icons.blocketLarge
        Brand.DBA -> if (small) icons.dba else icons.dbaLarge
        Brand.Finn -> if (small) icons.finn else icons.finnLarge
        Brand.FinnTagline -> icons.finnTagline
        Brand.Nettbil -> if (small) icons.nettbil else icons.nettbilLarge
        Brand.Oikotie -> if (small) icons.oikotie else icons.oikotieLarge
        Brand.Qasa -> if (small) icons.qasa else icons.qasaLarge
        Brand.Tori -> if (small) icons.tori else icons.toriLarge
        Brand.Vend -> if (small) icons.vend else icons.vendLarge
        Brand.VendPro -> icons.vendPro
        else -> null
    }
}

enum class Brand(val identifier: String) {
    Autovex("autovex"),
    Bilbasen("bilbasen"),
    Blocket("blocket"),
    DBA("dba"),
    Finn("finn"),
    FinnTagline("finntagline"),
    Nettbil("nettbil"),
    Oikotie("oikotie"),
    Qasa("qasa"),
    Tori("tori"),
    Vend("vend"),
    VendPro("vendpro")
}