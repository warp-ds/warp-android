package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.theme.WarpIcons
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.reflect.full.memberProperties

@RunWith(TestParameterInjector::class)
class WarpIconTest(
    @TestParameter val flavor: Flavor,
    @TestParameter val nightMode: NightMode,
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            nightMode = nightMode
        ),
        theme = "android:Theme.Material.Light.NoActionBar",
        renderingMode = SessionParams.RenderingMode.FULL_EXPAND,
        snapshotHandler = if (Config.isVerifying) {
            SnapshotVerifier(
                maxPercentDifference = Config.maxPercentDifference,
                rootDirectory = flavor.dir
            )
        } else {
            HtmlReportWriter(snapshotRootDirectory = flavor.dir)
        }
    )

    @Test
    fun warp_icon_count() {
        val count =
            WarpIcons::class.memberProperties.count { it.returnType.classifier == ImageVector::class }
        Assert.assertEquals(277, count)
    }

    @Test
    fun warp_icons_A() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.activeAds,
                    icons.ads,
                    icons.airCon,
                    icons.airplane,
                    icons.airplaneHotel,
                    icons.alert,
                    icons.allWheelDrive,
                    icons.animalPaw,
                    icons.archway,
                    icons.arrowDown,
                    icons.arrowLeft,
                    icons.arrowRight,
                    icons.arrowUp,
                    icons.attachement,
                    icons.automatic,
                    icons.autovex
                )
            )
        }
    }

    @Test
    fun warp_icons_B() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.babyOnesie,
                    icons.backWheelDrive,
                    icons.bag,
                    icons.bank,
                    icons.bankIdNo,
                    icons.batteryEmpty,
                    icons.batteryFull,
                    icons.batteryHalfFull,
                    icons.beach,
                    icons.bell,
                    icons.bellFilled,
                    icons.bin,
                    icons.block,
                    icons.boatLength,
                    icons.bolt,
                    icons.bookmark,
                    icons.browser,
                    icons.browserVerified,
                    icons.building,
                    icons.buildingPlot,
                    icons.bulb,
                    icons.bulldozer,
                    icons.burger,
                    icons.bus,
                )
            )
        }
    }

    @Test
    fun warp_icons_C() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.cabin,
                    icons.cabinHut,
                    icons.calculator,
                    icons.calendar,
                    icons.camera,
                    icons.camping,
                    icons.cancel,
                    icons.car,
                    icons.carFront,
                    icons.carKey,
                    icons.carPart,
                    icons.carRent,
                    icons.carService,
                    icons.carSubscription,
                    icons.cart,
                    icons.chainsaw,
                    icons.chair,
                    icons.charger,
                    icons.charter,
                    icons.chatRequest,
                    icons.chatSupport,
                    icons.check,
                    icons.checkList,
                    icons.checkShield,
                    icons.chevronDoubleLeft,
                    icons.chevronDoubleRight,
                    icons.chevronDown,
                    icons.chevronLeft,
                    icons.chevronRight,
                    icons.chevronUp,
                    icons.circlePlus,
                    icons.circlePlusFilled,
                    icons.circleUser,
                    icons.circleUserFilled,
                    icons.clock,
                    icons.close,
                    icons.cog,
                    icons.colorPalette,
                    icons.cottagePlot,
                    icons.creditCard,
                    icons.cursor,
                )
            )
        }
    }

    @Test
    fun warp_icons_D() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.dating,
                    icons.delivery,
                    icons.diner,
                    icons.discount,
                    icons.dislike,
                    icons.door,
                    icons.dots,
                    icons.doubleBed,
                    icons.download,
                    icons.drawer,
                    icons.drink,
                )
            )
        }
    }

    @Test
    fun warp_icons_E() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.economy,
                    icons.edit,
                    icons.energy,
                    icons.engine,
                    icons.engineBelt,
                    icons.error,
                    icons.exchange,
                    icons.expand,
                    icons.eyeOff,
                    icons.eyeOn,
                )
            )
        }
    }

    @Test
    fun warp_icons_F() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.facebook,
                    icons.farm,
                    icons.feedback,
                    icons.fileAdd,
                    icons.filter,
                    icons.fireplace,
                    icons.fishing,
                    icons.fitness,
                    icons.football,
                    icons.frontWheelDrive,
                )
            )
        }
    }

    @Test
    fun warp_icons_G() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.gasDiesel,
                    icons.gasFuel,
                    icons.gasHybrid,
                    icons.geometricShapes,
                    icons.graphLine,
                    icons.graphPie,
                    icons.grid,
                    icons.grill,
                    icons.guitarBat
                )
            )
        }
    }

    @Test
    fun warp_icons_H() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.headset,
                    icons.heart,
                    icons.heartRate,
                    icons.hiking,
                    icons.history,
                    icons.honk,
                    icons.hotel,
                    icons.house,
                    icons.houseBed,
                    icons.houseCabin,
                    icons.houseFilled,
                    icons.houseModern,
                    icons.housePerson,
                )
            )
        }
    }

    @Test
    fun warp_icons_I() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.iceSkater,
                    icons.image,
                    icons.info,
                    icons.instagram,
                )
            )
        }
    }

    @Test
    fun warp_icons_J() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.job,
                )
            )
        }
    }

    @Test
    fun warp_icons_K() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.keys,
                    icons.krone,
                )
            )
        }
    }

    @Test
    fun warp_icons_L() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.lamp,
                    icons.landscape,
                    icons.laundry,
                    icons.layers,
                    icons.leaf,
                    icons.lift,
                    icons.like,
                    icons.link,
                    icons.linkExternal,
                    icons.listSort,
                    icons.lockShield,
                    icons.logout,
                )
            )
        }
    }

    @Test
    fun warp_icons_M() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.mail,
                    icons.mailbox,
                    icons.manual,
                    icons.map,
                    icons.measure,
                    icons.message,
                    icons.messages,
                    icons.messagesFilled,
                    icons.minivan,
                    icons.minus,
                    icons.mittAnbud,
                    icons.mixer,
                    icons.money,
                    icons.motorcycle,
                    icons.mountain,
                )
            )
        }
    }

    @Test
    fun warp_icons_N() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.nettbil,
                    icons.newAd,
                    icons.norwegianMotor,
                    icons.noSmoking,
                )
            )
        }
    }

    @Test
    fun warp_icons_O() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.officeDesk,
                    icons.oikotie,
                    icons.organize,
                )
            )
        }
    }

    @Test
    fun warp_icons_P() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.paintRoller,
                    icons.parking,
                    icons.paw,
                    icons.phone,
                    icons.phoneBadgeCheck,
                    icons.phoneScratched,
                    icons.phoneUsed,
                    icons.pictureStack,
                    icons.pinMarker,
                    icons.pinRound,
                    icons.planeTicket,
                    icons.play,
                    icons.playhouse,
                    icons.plots,
                    icons.plus,
                    icons.productBlink,
                    icons.productBump,
                    icons.productCarousel,
                    icons.productHighlightListing,
                    icons.productNaBolagsProfil,
                    icons.productNoAds,
                    icons.productStarred,
                    icons.productTop,
                    icons.propeller,
                )
            )
        }
    }

    @Test
    fun warp_icons_Q() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.question,
                )
            )
        }
    }

    @Test
    fun warp_icons_R() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.realEstate,
                    icons.refresh,
                    icons.remppatori,
                    icons.roomService,
                )
            )
        }
    }

    @Test
    fun warp_icons_S() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.sailboat,
                    icons.sailing,
                    icons.sauna,
                    icons.scrollUp,
                    icons.search,
                    icons.searchFavorites,
                    icons.seat,
                    icons.send,
                    icons.service,
                    icons.share,
                    icons.shipping,
                    icons.shirt,
                    icons.shoes,
                    icons.shoppingCart,
                    icons.shower,
                    icons.skyscraper,
                    icons.smartphone,
                    icons.smileyGood,
                    icons.smileyHappy,
                    icons.smileyNeutral,
                    icons.smileySad,
                    icons.sofa,
                    icons.sorting,
                    icons.spa,
                    icons.sparkles,
                    icons.speedometer,
                    icons.stairs,
                    icons.starCheck,
                    icons.starEmpty,
                    icons.starFull,
                    icons.starHalf,
                    icons.store,
                    icons.storeFront,
                    icons.stove,
                    icons.stroller,
                    icons.success,
                    icons.suitcase,
                    icons.support,
                    icons.sweater,
                    icons.swimming,
                )
            )
        }
    }

    @Test
    fun warp_icons_T() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.tag,
                    icons.taskList,
                    icons.textBold,
                    icons.textItalic,
                    icons.textStrikethrough,
                    icons.textUnderline,
                    icons.theater,
                    icons.threeSixty,
                    icons.tools,
                    icons.townhouse,
                    icons.tractor,
                    icons.trendDown,
                    icons.trendFlat,
                    icons.trendUp,
                    icons.tv,
                    icons.twitter,
                )
            )
        }
    }

    @Test
    fun warp_icons_U() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.up,
                    icons.upload,
                    icons.user,
                    icons.userGroup,
                    icons.users,
                    icons.userWoman,
                )
            )
        }
    }

    @Test
    fun warp_icons_V() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.vacuum,
                    icons.van,
                    icons.vase,
                    icons.verification,
                )
            )
        }
    }

    @Test
    fun warp_icons_W() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.wallet,
                    icons.warning,
                    icons.warranty,
                    icons.wheelchair,
                    icons.wifi,
                    icons.woods,
                )
            )
        }
    }

    @Test
    fun warp_icons_Y() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.youtube
                )
            )
        }
    }

    @Composable
    private fun WarpIconsAllSizes(icons: List<ImageVector>) {
        WarpTheme(flavor = flavor) {
            Column(
                modifier = Modifier
                    .background(colors.surface.elevated100)
                    .padding(
                        horizontal = dimensions.space2,
                        vertical = dimensions.space2
                    ),
                verticalArrangement = Arrangement.spacedBy(dimensions.space2)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(dimensions.space2),
                        modifier = Modifier

                            .padding(dimensions.space2)
                    ) {
                        icons.forEach { CreateIcon(it, it.name, dimensions.icon.small) }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(dimensions.space2),
                        modifier = Modifier
                            .padding(dimensions.space2)
                    ) {
                        icons.forEach { CreateIcon(it, it.name, dimensions.icon.default) }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(dimensions.space2),
                        modifier = Modifier
                            .padding(dimensions.space2)
                    ) {
                        icons.forEach { CreateIcon(it, it.name, dimensions.icon.large) }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(dimensions.space2),
                        modifier = Modifier
                            .padding(dimensions.space2)
                    ) {
                        icons.forEach { CreateIcon(it, it.name, 28.dp) }
                    }
                }
            }
        }

    }

    @Composable
    private fun CreateIcon(icon: ImageVector, iconDescription: String, size: Dp) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            tint = colors.icon.primary,
            modifier = Modifier.size(size)
        )
    }
}
