package com.nmp.warp.brands

enum class Brand {
    FINN,
    TORI,
}

fun getBrandFromFlavor(flavor: String): Brand {
    return Brand.valueOf(flavor.uppercase())
}