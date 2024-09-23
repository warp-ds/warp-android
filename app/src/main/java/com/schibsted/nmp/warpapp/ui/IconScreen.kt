package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun IconScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Icons", onUp = onUp) {
        CheckboxScreenContent()
    }
}

@Composable
private fun CheckboxScreenContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(WarpTheme.dimensions.space2),
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(WarpTheme.dimensions.space2)
    ) {
        Icon(
            icons.activeAds,
            contentDescription = "Active ads icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.ads,
            contentDescription = "Ads icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.airCon,
            contentDescription = "Air condition icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.allWheelDrive,
            contentDescription = "All wheel drive icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.animalPaw,
            contentDescription = "Animal paw icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.archway,
            contentDescription = "Archway icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.arrowDown,
            contentDescription = "Arrow down icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.arrowLeft,
            contentDescription = "Arrow left icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.arrowRight,
            contentDescription = "Arrow right icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.arrowUp,
            contentDescription = "Arrow up icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.attachement,
            contentDescription = "Attachment icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.automatic,
            contentDescription = "Automatic icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.backWheelDrive,
            contentDescription = "Back wheel drive icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.bag,
            contentDescription = "Bag icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.bank,
            contentDescription = "Bank icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.bankIdNo,
            contentDescription = "Bank ID number icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.batteryEmpty,
            contentDescription = "Battery empty icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.batteryFull,
            contentDescription = "Battery full icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.batteryHalfFull,
            contentDescription = "Battery half full icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.beach,
            contentDescription = "Beach icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.bell,
            contentDescription = "Bell icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.bellFilled,
            contentDescription = "Bell filled icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.bin,
            contentDescription = "Bin icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.block,
            contentDescription = "Block icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.boatLength,
            contentDescription = "Boat length icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.bolt,
            contentDescription = "Bolt icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.bookmark,
            contentDescription = "Bookmark icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.browser,
            contentDescription = "Browser icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.browserVerified,
            contentDescription = "Browser verified icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.building,
            contentDescription = "Building icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.buildingPlot,
            contentDescription = "Building plot icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.bulb,
            contentDescription = "Bulb icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.burger,
            contentDescription = "Burger menu icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.bus,
            contentDescription = "Bus icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.cabinHut,
            contentDescription = "Cabin hut icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.calculator,
            contentDescription = "Calculator icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.calendar,
            contentDescription = "Calendar icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.camera,
            contentDescription = "Camera icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.cancel,
            contentDescription = "Cancel icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.car,
            contentDescription = "Car icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.carKey,
            contentDescription = "Car key icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.carService,
            contentDescription = "Car service icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.charger,
            contentDescription = "Charger icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.charter,
            contentDescription = "Charter icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.chatRequest,
            contentDescription = "Chat request icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.chatSupport,
            contentDescription = "Chat support icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.check,
            contentDescription = "Check icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.checkList,
            contentDescription = "Checklist icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.checkShield,
            contentDescription = "Check and shield icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.chevronDoubleLeft,
            contentDescription = "Double left chevron icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.chevronDoubleRight,
            contentDescription = "Double right chevron icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.chevronDown,
            contentDescription = "Down chevron icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.chevronLeft,
            contentDescription = "Left chevron icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.chevronRight,
            contentDescription = "Right chevron icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.chevronUp,
            contentDescription = "Up chevron icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.circlePlus,
            contentDescription = "Circle plus icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.circlePlusFilled,
            contentDescription = "Circle plus filled icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.circleUser,
            contentDescription = "Circle user icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.circleUserFilled,
            contentDescription = "Circle user filled icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.clock,
            contentDescription = "Clock icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.close,
            contentDescription = "Close icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.cog,
            contentDescription = "Cog icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.colorPalette,
            contentDescription = "Color palette icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.cottagePlot,
            contentDescription = "Cottage plot icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.creditCard,
            contentDescription = "Credit card icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.cursor,
            contentDescription = "Cursor icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.delivery,
            contentDescription = "Delivery icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.diner,
            contentDescription = "Diner icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.discount,
            contentDescription = "Discount icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.dislike,
            contentDescription = "Dislike icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.door,
            contentDescription = "Door icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.dots,
            contentDescription = "Dots icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.doubleBed,
            contentDescription = "Double bed icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.download,
            contentDescription = "Download icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.drawer,
            contentDescription = "Drawer icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.drink,
            contentDescription = "Drink icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.edit,
            contentDescription = "Edit icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.energy,
            contentDescription = "Energy icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.engine,
            contentDescription = "Engine icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
        Icon(
            imageVector = icons.engineBelt,
            contentDescription = "Engine belt icon",
            tint = WarpTheme.colors.icon.primary,
            modifier = Modifier.size(WarpTheme.dimensions.space4)
        )
    }
}