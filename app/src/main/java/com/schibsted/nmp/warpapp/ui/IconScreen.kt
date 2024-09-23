package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun IconScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Icons", onUp = onUp) {
        CheckboxScreenContent()
    }
}

@Composable
private fun CheckboxScreenContent() {
        Row(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensions.space2),
                modifier = Modifier

                    .padding(dimensions.space2)
            ) {
                CreateIcons(dimensions.icon.small)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensions.space2),
                modifier = Modifier
                    .padding(dimensions.space2)
            ) {
                CreateIcons(dimensions.icon.default)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensions.space2),
                modifier = Modifier
                    .padding(dimensions.space2)
            ) {
                CreateIcons(dimensions.icon.large)
            }
        }
}

@Composable
fun CreateIcons(size: Dp) {
    CreateIcon(icons.activeAds, "Active ads icon", size)
    CreateIcon(icons.ads, "Ads icon", size)
    CreateIcon(icons.airCon, "Air condition icon", size)
    CreateIcon(icons.allWheelDrive, "All wheel drive icon", size)
    CreateIcon(icons.animalPaw, "Animal paw icon", size)
    CreateIcon(icons.archway, "Archway icon", size)
    CreateIcon(icons.arrowDown, "Arrow down icon", size)
    CreateIcon(icons.arrowLeft, "Arrow left icon", size)
    CreateIcon(icons.arrowRight, "Arrow right icon", size)
    CreateIcon(icons.arrowUp, "Arrow up icon", size)
    CreateIcon(icons.attachement, "Attachment icon", size)
    CreateIcon(icons.automatic, "Automatic icon", size)
    CreateIcon(icons.backWheelDrive, "Back wheel drive icon", size)
    CreateIcon(icons.bag, "Bag icon", size)
    CreateIcon(icons.bank, "Bank icon", size)
    CreateIcon(icons.bankIdNo, "Bank ID number icon", size)
    CreateIcon(icons.batteryEmpty, "Battery empty icon", size)
    CreateIcon(icons.batteryFull, "Battery full icon", size)
    CreateIcon(icons.batteryHalfFull, "Battery half full icon", size)
    CreateIcon(icons.beach, "Beach icon", size)
    CreateIcon(icons.bell, "Bell icon", size)
    CreateIcon(icons.bellFilled, "Bell filled icon", size)
    CreateIcon(icons.bin, "Bin icon", size)
    CreateIcon(icons.block, "Block icon", size)
    CreateIcon(icons.boatLength, "Boat length icon", size)
    CreateIcon(icons.bolt, "Bolt icon", size)
    CreateIcon(icons.bookmark, "Bookmark icon", size)
    CreateIcon(icons.browser, "Browser icon", size)
    CreateIcon(icons.browserVerified, "Browser verified icon", size)
    CreateIcon(icons.building, "Building icon", size)
    CreateIcon(icons.buildingPlot, "Building plot icon", size)
    CreateIcon(icons.bulb, "Bulb icon", size)
    CreateIcon(icons.burger, "Burger menu icon", size)
    CreateIcon(icons.bus, "Bus icon", size)
    CreateIcon(icons.cabinHut, "Cabin hut icon", size)
    CreateIcon(icons.calculator, "Calculator icon", size)
    CreateIcon(icons.calendar, "Calendar icon", size)
    CreateIcon(icons.camera, "Camera icon", size)
    CreateIcon(icons.cancel, "Cancel icon", size)
    CreateIcon(icons.car, "Car icon", size)
    CreateIcon(icons.carKey, "Car key icon", size)
    CreateIcon(icons.carService, "Car service icon", size)
    CreateIcon(icons.charger, "Charger icon", size)
    CreateIcon(icons.charter, "Charter icon", size)
    CreateIcon(icons.chatRequest, "Chat request icon", size)
    CreateIcon(icons.chatSupport, "Chat support icon", size)
    CreateIcon(icons.check, "Check icon", size)
    CreateIcon(icons.checkList, "Checklist icon", size)
    CreateIcon(icons.checkShield, "Check and shield icon", size)
    CreateIcon(icons.chevronDoubleLeft, "Double left chevron icon", size)
    CreateIcon(icons.chevronDoubleRight, "Double right chevron icon", size)
    CreateIcon(icons.chevronDown, "Down chevron icon", size)
    CreateIcon(icons.chevronLeft, "Left chevron icon", size)
    CreateIcon(icons.chevronRight, "Right chevron icon", size)
    CreateIcon(icons.chevronUp, "Up chevron icon", size)
    CreateIcon(icons.circlePlus, "Circle plus icon", size)
    CreateIcon(icons.circlePlusFilled, "Circle plus filled icon", size)
    CreateIcon(icons.circleUser, "Circle user icon", size)
    CreateIcon(icons.circleUserFilled, "Circle user filled icon", size)
    CreateIcon(icons.clock, "Clock icon", size)
    CreateIcon(icons.close, "Close icon", size)
    CreateIcon(icons.cog, "Cog icon", size)
    CreateIcon(icons.colorPalette, "Color palette icon", size)
    CreateIcon(icons.cottagePlot, "Cottage plot icon", size)
    CreateIcon(icons.creditCard, "Credit card icon", size)
    CreateIcon(icons.cursor, "Cursor icon", size)
    CreateIcon(icons.delivery, "Delivery icon", size)
    CreateIcon(icons.diner, "Diner icon", size)
    CreateIcon(icons.discount, "Discount icon", size)
    CreateIcon(icons.dislike, "Dislike icon", size)
    CreateIcon(icons.door, "Door icon", size)
    CreateIcon(icons.dots, "Dots icon", size)
    CreateIcon(icons.doubleBed, "Double bed icon", size)
    CreateIcon(icons.download, "Download icon", size)
    CreateIcon(icons.drawer, "Drawer icon", size)
    CreateIcon(icons.drink, "Drink icon", size)
    CreateIcon(icons.edit, "Edit icon", size)
    CreateIcon(icons.energy, "Energy icon", size)
    CreateIcon(icons.engine, "Engine icon", size)
    CreateIcon(icons.engineBelt, "Engine belt icon", size)
    CreateIcon(icons.error, "Error icon", size)
    CreateIcon(icons.exchange, "Exchange icon", size)
    CreateIcon(icons.expand, "Expand icon", size)
    CreateIcon(icons.eyeOff, "Eye off icon", size)
    CreateIcon(icons.eyeOn, "Eye on icon", size)
    CreateIcon(icons.facebook, "Facebook icon", size)
    CreateIcon(icons.farm, "Farm icon", size)
    CreateIcon(icons.feedback, "Feedback icon", size)
    CreateIcon(icons.fileAdd, "Add file icon", size)
    CreateIcon(icons.filter, "Filter icon", size)
    CreateIcon(icons.fireplace, "Fireplace icon", size)
    CreateIcon(icons.fishing, "Fishing icon", size)
    CreateIcon(icons.fitness, "Fitness icon", size)
    CreateIcon(icons.frontWheelDrive, "Front wheel drive icon", size)
    CreateIcon(icons.gasDiesel, "Diesel fuel icon", size)
    CreateIcon(icons.gasFuel, "Fuel icon", size)
    CreateIcon(icons.gasHybrid, "Hybrid fuel icon", size)
    CreateIcon(icons.graphLine, "Graph line icon", size)
    CreateIcon(icons.graphPie, "Graph pie icon", size)
    CreateIcon(icons.grid, "Grid icon", size)
    CreateIcon(icons.grill, "Grill icon", size)
    CreateIcon(icons.headset, "Headset icon", size)
    CreateIcon(icons.heart, "Heart icon", size)
    CreateIcon(icons.heartRate, "Heart rate icon", size)
    CreateIcon(icons.hiking, "Hiking icon", size)
    CreateIcon(icons.history, "History icon", size)
    CreateIcon(icons.house, "House icon", size)
    CreateIcon(icons.houseBed, "House bed icon", size)
    CreateIcon(icons.houseCabin, "House cabin icon", size)
    CreateIcon(icons.houseFilled, "House filled icon", size)
    CreateIcon(icons.houseModern, "House modern icon", size)
    CreateIcon(icons.housePerson, "House person icon", size)
    CreateIcon(icons.image, "Image icon", size)
    CreateIcon(icons.info, "Info icon", size)
    CreateIcon(icons.instagram, "Instagram icon", size)
    CreateIcon(icons.keys, "Keys icon", size)
    CreateIcon(icons.krone, "Krone icon", size)
    CreateIcon(icons.lamp, "Lamp icon", size)
    CreateIcon(icons.laundry, "Laundry icon", size)
}

@Composable
private fun CreateIcon(icon: ImageVector, iconDescription: String, size: Dp) {
    Icon(
        imageVector = icon,
        contentDescription = iconDescription,
        tint = WarpTheme.colors.icon.primary,
        modifier = Modifier.size(size)
    )
}