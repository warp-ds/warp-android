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
    CreateIcon(icons.layers, "Layers icon", size)
    CreateIcon(icons.leaf, "Leaf icon", size)
    CreateIcon(icons.lift, "Lift icon", size)
    CreateIcon(icons.like, "Like icon", size)
    CreateIcon(icons.link, "Link icon", size)
    CreateIcon(icons.linkExternal, "External link icon", size)
    CreateIcon(icons.listSort, "List sort icon", size)
    CreateIcon(icons.lockShield, "Lock shield icon", size)
    CreateIcon(icons.logout, "Logout icon", size)
    CreateIcon(icons.mail, "Mail icon", size)
    CreateIcon(icons.mailbox, "Mailbox icon", size)
    CreateIcon(icons.manual, "Manual icon", size)
    CreateIcon(icons.map, "Map icon", size)
    CreateIcon(icons.measure, "Measure icon", size)
    CreateIcon(icons.message, "Message icon", size)
    CreateIcon(icons.messages, "Messages icon", size)
    CreateIcon(icons.messagesFilled, "Messages filled icon", size)
    CreateIcon(icons.minus, "Minus icon", size)
    CreateIcon(icons.mixer, "Mixer icon", size)
    CreateIcon(icons.money, "Money icon", size)
    CreateIcon(icons.mountain, "Mountain icon", size)
    CreateIcon(icons.newAd, "New ad icon", size)
    CreateIcon(icons.norwegianMotor, "Norwegian motorhome icon", size)
    CreateIcon(icons.noSmoking, "No smoking icon", size)
    CreateIcon(icons.officeDesk, "Office desk icon", size)
    CreateIcon(icons.organize, "Organize icon", size)
    CreateIcon(icons.parking, "Parking icon", size)
    CreateIcon(icons.phone, "Phone icon", size)
    CreateIcon(icons.phoneScratched, "Phone scratched icon", size)
    CreateIcon(icons.phoneUsed, "Phone used icon", size)
    CreateIcon(icons.pictureStack, "Picture stack icon", size)
    CreateIcon(icons.pinMarker, "Pin marker icon", size)
    CreateIcon(icons.pinRound, "Round pin icon", size)
    CreateIcon(icons.planeTicket, "Plane ticket icon", size)
    CreateIcon(icons.play, "Play icon", size)
    CreateIcon(icons.playhouse, "Playhouse icon", size)
    CreateIcon(icons.plots, "Plots icon", size)
    CreateIcon(icons.plus, "Plus icon", size)
    CreateIcon(icons.productBlink, "Product blink icon", size)
    CreateIcon(icons.productBump, "Product bump icon", size)
    CreateIcon(icons.productCarousel, "Product carousel icon", size)
    CreateIcon(icons.productHighlightListing, "Product highlight listing icon", size)
    CreateIcon(icons.productNaBolagsProfil, "Product na bolagsprofil icon", size)
    CreateIcon(icons.productNoAds, "Product no ads icon", size)
    CreateIcon(icons.productStarred, "Product starred icon", size)
    CreateIcon(icons.productTop, "Product top icon", size)
    CreateIcon(icons.propeller, "Propeller icon", size)
    CreateIcon(icons.question, "Question icon", size)
    CreateIcon(icons.refresh, "Refresh icon", size)
    CreateIcon(icons.roomService, "Room service icon", size)
    CreateIcon(icons.sailing, "Sailing icon", size)
    CreateIcon(icons.sauna, "Sauna icon", size)
    CreateIcon(icons.scrollUp, "Scroll up icon", size)
    CreateIcon(icons.search, "Search icon", size)
    CreateIcon(icons.searchFavorites, "Search favorites icon", size)
    CreateIcon(icons.seat, "Seat icon", size)
    CreateIcon(icons.send, "Send icon", size)
    CreateIcon(icons.service, "Service icon", size)
    CreateIcon(icons.share, "Share icon", size)
    CreateIcon(icons.shipping, "Shipping icon", size)
    CreateIcon(icons.shoes, "Shoes icon", size)
    CreateIcon(icons.shoppingCart, "Shopping cart icon", size)
    CreateIcon(icons.shower, "Shower icon", size)
    CreateIcon(icons.skyscraper, "Skyscraper icon", size)
    CreateIcon(icons.smileyGood, "Good smiley icon", size)
    CreateIcon(icons.smileyHappy, "Happy smiley icon", size)
    CreateIcon(icons.smileyNeutral, "Neutral smiley icon", size)
    CreateIcon(icons.smileySad, "Sad smiley icon", size)
    CreateIcon(icons.sorting, "Sorting icon", size)
    CreateIcon(icons.spa, "Spa icon", size)
    CreateIcon(icons.speedometer, "Speedometer icon", size)
    CreateIcon(icons.stairs, "Stairs icon", size)
    CreateIcon(icons.starCheck, "Star check icon", size)
    CreateIcon(icons.starEmpty, "Star empty icon", size)
    CreateIcon(icons.starFull, "Star full icon", size)
    CreateIcon(icons.starHalf, "Star half icon", size)
    CreateIcon(icons.store, "Store icon", size)
    CreateIcon(icons.stove, "Stove icon", size)
    CreateIcon(icons.success, "Success icon", size)
    CreateIcon(icons.suitcase, "Suitcase icon", size)
    CreateIcon(icons.support, "Support icon", size)
    CreateIcon(icons.swimming, "Swimming icon", size)
    CreateIcon(icons.tag, "Tag icon", size)
    CreateIcon(icons.taskList, "Task list icon", size)
    CreateIcon(icons.textBold, "Bold text icon", size)
    CreateIcon(icons.textItalic, "Italic text icon", size)
    CreateIcon(icons.textStrikethrough, "Strikethrough text icon", size)
    CreateIcon(icons.textUnderline, "Underline text icon", size)
    CreateIcon(icons.theater, "Theater icon", size)
    CreateIcon(icons.threeSixty, "360 degrees icon", size)
    CreateIcon(icons.townhouse, "Townhouse icon", size)
    CreateIcon(icons.tv, "TV icon", size)
    CreateIcon(icons.twitter, "Twitter icon", size)
    CreateIcon(icons.up, "Up icon", size)
    CreateIcon(icons.upload, "Upload icon", size)
    CreateIcon(icons.user, "User icon", size)
    CreateIcon(icons.userGroup, "User group icon", size)
    CreateIcon(icons.users, "Users icon", size)
    CreateIcon(icons.userWoman, "User woman icon", size)
    CreateIcon(icons.vacuum, "Vacuum icon", size)
    CreateIcon(icons.verification, "Verification icon", size)
    CreateIcon(icons.wallet, "Wallet icon", size)
    CreateIcon(icons.warning, "Warning icon", size)
    CreateIcon(icons.warranty, "Warranty icon", size)
    CreateIcon(icons.wheelchair, "Wheelchair icon", size)
    CreateIcon(icons.wifi, "Wifi icon", size)
    CreateIcon(icons.woods, "Woods icon", size)
    CreateIcon(icons.youtube, "Youtube icon", size)
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