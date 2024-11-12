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
import com.schibsted.nmp.warp.theme.WarpIconResources
import com.schibsted.nmp.warp.theme.WarpResources.vectors
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
            WarpIconResources::class.memberProperties.count { it.returnType.classifier == ImageVector::class }
        Assert.assertEquals(280, count)
    }

    @Test
    fun warp_icons_A() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.activeAds,
                    vectors.ads,
                    vectors.airCon,
                    vectors.airplane,
                    vectors.airplaneHotel,
                    vectors.alert,
                    vectors.allWheelDrive,
                    vectors.animalPaw,
                    vectors.archway,
                    vectors.arrowDown,
                    vectors.arrowLeft,
                    vectors.arrowRight,
                    vectors.arrowUp,
                    vectors.attachement,
                    vectors.automatic,
                    vectors.autovex,
                    vectors.awardMedal
                )
            )
        }
    }

    @Test
    fun warp_icons_B() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.babyOnesie,
                    vectors.backWheelDrive,
                    vectors.bag,
                    vectors.bank,
                    vectors.bankIdNo,
                    vectors.batteryEmpty,
                    vectors.batteryFull,
                    vectors.batteryHalfFull,
                    vectors.beach,
                    vectors.bell,
                    vectors.bellFilled,
                    vectors.bin,
                    vectors.block,
                    vectors.boatLength,
                    vectors.bolt,
                    vectors.bookmark,
                    vectors.browser,
                    vectors.browserVerified,
                    vectors.building,
                    vectors.buildingPlot,
                    vectors.bulb,
                    vectors.bulldozer,
                    vectors.burger,
                    vectors.bus,
                )
            )
        }
    }

    @Test
    fun warp_icons_C() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.cabin,
                    vectors.cabinHut,
                    vectors.calculator,
                    vectors.calendar,
                    vectors.camera,
                    vectors.camping,
                    vectors.cancel,
                    vectors.car,
                    vectors.carFront,
                    vectors.carKey,
                    vectors.carPart,
                    vectors.carRent,
                    vectors.carService,
                    vectors.carSubscription,
                    vectors.cart,
                    vectors.chainsaw,
                    vectors.chair,
                    vectors.charger,
                    vectors.charter,
                    vectors.chatRequest,
                    vectors.chatSupport,
                    vectors.check,
                    vectors.checkList,
                    vectors.checkShield,
                    vectors.chevronDoubleLeft,
                    vectors.chevronDoubleRight,
                    vectors.chevronDown,
                    vectors.chevronLeft,
                    vectors.chevronRight,
                    vectors.chevronUp,
                    vectors.circlePlus,
                    vectors.circlePlusFilled,
                    vectors.circleUser,
                    vectors.circleUserFilled,
                    vectors.clock,
                    vectors.close,
                    vectors.cog,
                    vectors.colorPalette,
                    vectors.cottagePlot,
                    vectors.creditCard,
                    vectors.cursor,
                )
            )
        }
    }

    @Test
    fun warp_icons_D() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.dating,
                    vectors.delivery,
                    vectors.diner,
                    vectors.discount,
                    vectors.dislike,
                    vectors.door,
                    vectors.dots,
                    vectors.dotsVertical,
                    vectors.doubleBed,
                    vectors.download,
                    vectors.drawer,
                    vectors.drink,
                )
            )
        }
    }

    @Test
    fun warp_icons_E() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.economy,
                    vectors.edit,
                    vectors.energy,
                    vectors.engine,
                    vectors.engineBelt,
                    vectors.error,
                    vectors.exchange,
                    vectors.expand,
                    vectors.eyeOff,
                    vectors.eyeOn,
                )
            )
        }
    }

    @Test
    fun warp_icons_F() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.facebook,
                    vectors.farm,
                    vectors.feedback,
                    vectors.fileAdd,
                    vectors.filter,
                    vectors.fireplace,
                    vectors.fishing,
                    vectors.fitness,
                    vectors.football,
                    vectors.frontWheelDrive,
                )
            )
        }
    }

    @Test
    fun warp_icons_G() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.gasDiesel,
                    vectors.gasFuel,
                    vectors.gasHybrid,
                    vectors.geometricShapes,
                    vectors.graphLine,
                    vectors.graphPie,
                    vectors.grid,
                    vectors.grill,
                    vectors.guitarBat
                )
            )
        }
    }

    @Test
    fun warp_icons_H() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.headset,
                    vectors.heart,
                    vectors.heartRate,
                    vectors.hiking,
                    vectors.history,
                    vectors.honk,
                    vectors.hotel,
                    vectors.house,
                    vectors.houseBed,
                    vectors.houseCabin,
                    vectors.houseFilled,
                    vectors.houseModern,
                    vectors.housePerson,
                )
            )
        }
    }

    @Test
    fun warp_icons_I() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.iceSkater,
                    vectors.image,
                    vectors.info,
                    vectors.instagram,
                )
            )
        }
    }

    @Test
    fun warp_icons_J() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.job,
                )
            )
        }
    }

    @Test
    fun warp_icons_K() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.keys,
                    vectors.krone,
                )
            )
        }
    }

    @Test
    fun warp_icons_L() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.lamp,
                    vectors.landscape,
                    vectors.laundry,
                    vectors.layers,
                    vectors.leaf,
                    vectors.lift,
                    vectors.like,
                    vectors.link,
                    vectors.linkExternal,
                    vectors.listSort,
                    vectors.lockShield,
                    vectors.logout,
                )
            )
        }
    }

    @Test
    fun warp_icons_M() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.mail,
                    vectors.mailbox,
                    vectors.manual,
                    vectors.map,
                    vectors.measure,
                    vectors.message,
                    vectors.messages,
                    vectors.messagesFilled,
                    vectors.minivan,
                    vectors.minus,
                    vectors.mittAnbud,
                    vectors.mixer,
                    vectors.money,
                    vectors.motorcycle,
                    vectors.mountain,
                )
            )
        }
    }

    @Test
    fun warp_icons_N() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.nettbil,
                    vectors.newAd,
                    vectors.norwegianMotor,
                    vectors.noSmoking,
                )
            )
        }
    }

    @Test
    fun warp_icons_O() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.officeDesk,
                    vectors.oikotie,
                    vectors.organize,
                )
            )
        }
    }

    @Test
    fun warp_icons_P() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.paintRoller,
                    vectors.parking,
                    vectors.paw,
                    vectors.phone,
                    vectors.phoneBadgeCheck,
                    vectors.phoneScratched,
                    vectors.phoneUsed,
                    vectors.pictureStack,
                    vectors.pinMarker,
                    vectors.pinRound,
                    vectors.planeTicket,
                    vectors.play,
                    vectors.playhouse,
                    vectors.plots,
                    vectors.plus,
                    vectors.productBlink,
                    vectors.productBump,
                    vectors.productCarousel,
                    vectors.productHighlightListing,
                    vectors.productNaBolagsProfil,
                    vectors.productNoAds,
                    vectors.productStarred,
                    vectors.productTop,
                    vectors.propeller,
                )
            )
        }
    }

    @Test
    fun warp_icons_Q() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.question,
                )
            )
        }
    }

    @Test
    fun warp_icons_R() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.realEstate,
                    vectors.refresh,
                    vectors.remppatori,
                    vectors.roomService,
                )
            )
        }
    }

    @Test
    fun warp_icons_S() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.sailboat,
                    vectors.sailing,
                    vectors.sauna,
                    vectors.scrollUp,
                    vectors.search,
                    vectors.searchFavorites,
                    vectors.seat,
                    vectors.send,
                    vectors.service,
                    vectors.share,
                    vectors.shipping,
                    vectors.shirt,
                    vectors.shoes,
                    vectors.shoppingCart,
                    vectors.shower,
                    vectors.skyscraper,
                    vectors.smartphone,
                    vectors.smileyGood,
                    vectors.smileyHappy,
                    vectors.smileyNeutral,
                    vectors.smileySad,
                    vectors.sofa,
                    vectors.sorting,
                    vectors.spa,
                    vectors.sparkles,
                    vectors.speedometer,
                    vectors.stairs,
                    vectors.starCheck,
                    vectors.starEmpty,
                    vectors.starFull,
                    vectors.starHalf,
                    vectors.store,
                    vectors.storeFront,
                    vectors.stove,
                    vectors.stroller,
                    vectors.success,
                    vectors.suitcase,
                    vectors.support,
                    vectors.sweater,
                    vectors.swimming,
                )
            )
        }
    }

    @Test
    fun warp_icons_T() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.tag,
                    vectors.taskList,
                    vectors.textBold,
                    vectors.textItalic,
                    vectors.textStrikethrough,
                    vectors.textUnderline,
                    vectors.theater,
                    vectors.threeSixty,
                    vectors.tools,
                    vectors.townhouse,
                    vectors.tractor,
                    vectors.trendDown,
                    vectors.trendFlat,
                    vectors.trendUp,
                    vectors.tv,
                    vectors.twitter,
                )
            )
        }
    }

    @Test
    fun warp_icons_U() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.up,
                    vectors.upload,
                    vectors.user,
                    vectors.userGroup,
                    vectors.users,
                    vectors.userWoman,
                )
            )
        }
    }

    @Test
    fun warp_icons_V() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.vacuum,
                    vectors.van,
                    vectors.vase,
                    vectors.verification,
                )
            )
        }
    }

    @Test
    fun warp_icons_W() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.wallet,
                    vectors.warning,
                    vectors.warranty,
                    vectors.waterPitcher,
                    vectors.wheelchair,
                    vectors.wifi,
                    vectors.woods,
                )
            )
        }
    }

    @Test
    fun warp_icons_Y() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    vectors.youtube
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
