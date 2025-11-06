package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.theme.WarpBrandIconResource
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpIconResources
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTaxonomyIconResource
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
            WarpIconResources::class.memberProperties.count {
                it.returnType.classifier == WarpIconResource::class
                        || it.returnType.classifier == WarpTaxonomyIconResource::class
                        || it.returnType.classifier == WarpBrandIconResource::class
            }
        Assert.assertEquals(266, count)
    }

    @Test
    fun warp_icons_A() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.activeAds,
                    icons.ads,
                    icons.airCon,
                    icons.airplaneBed,
                    icons.allWheelDrive,
                    icons.animalPaw,
                    icons.archiveBox,
                    icons.archway,
                    icons.arrowDown,
                    icons.arrowLeft,
                    icons.arrowRight,
                    icons.arrowUp,
                    icons.autovex,
                    icons.awardMedal
                )
            )
        }
    }

    @Test
    fun warp_icons_B() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
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
                    icons.boat,
                    icons.bolt,
                    icons.bookmark,
                    icons.briefcaseUser,
                    icons.briefcase,
                    icons.browser,
                    icons.browserVerified,
                    icons.building,
                    icons.buildingPlot,
                    icons.bulb,
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
                    icons.cabinHut,
                    icons.calculator,
                    icons.calendar,
                    icons.calendarBlank,
                    icons.calendarPlus,
                    icons.camera,
                    icons.cancel,
                    icons.car,
                    icons.carLeft,
                    icons.carPart,
                    icons.carRent,
                    icons.carRight,
                    icons.chair,
                    icons.charger,
                    icons.charter,
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
                    icons.clockCountDown,
                    icons.close,
                    icons.code,
                    icons.cog,
                    icons.colorPalette,
                    icons.confetti,
                    icons.cookie,
                    icons.copy,
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
                    icons.delivery,
                    icons.deliveryKey,
                    icons.diner,
                    icons.discount,
                    icons.dislike,
                    icons.door,
                    icons.dots,
                    icons.dotsVertical,
                    icons.doubleBed,
                    icons.download,
                    icons.dragDots,
                    icons.draw,
                    icons.drawer,
                    icons.drill,
                    icons.drink,
                    icons.dumbbell,
                    )
            )
        }
    }

    @Test
    fun warp_icons_E() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
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
                    icons.fileAdd,
                    icons.fileArrowDown,
                    icons.filter,
                    icons.fireplace,
                    icons.fishing,
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
                    icons.gearManual,
                    icons.geometricShapes,
                    icons.globe,
                    icons.gearAutomatic,
                    icons.graphLine,
                    icons.graphPie,
                    icons.grid,
                    icons.grill,
                    icons.guitar
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
                    icons.house,
                    icons.houseBed,
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
                    icons.jetSki
                )
            )
        }
    }

    @Test
    fun warp_icons_K() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.key,
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
                    icons.layers,
                    icons.leaf,
                    icons.lift,
                    icons.like,
                    icons.link,
                    icons.linkExternal,
                    icons.listSort,
                    icons.lockShield,
                    icons.logout,
                    icons.lotusFlower,
                    icons.luggage
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
                    icons.map,
                    icons.megaphone,
                    icons.messages,
                    icons.messagesFilled,
                    icons.minus,
                    icons.mittAnbud,
                    icons.mixer,
                    icons.money,
                    icons.moon,
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
                    icons.paperclip,
                    icons.parking,
                    icons.phone,
                    icons.phoneCheck,
                    icons.phoneScratched,
                    icons.phoneUsed,
                    icons.pictureStack,
                    icons.pinMarker,
                    icons.pinRound,
                    icons.planeLand,
                    icons.planeTakeOff,
                    icons.play,
                    icons.playhouse,
                    icons.plus,
                    icons.productBump,
                    icons.productCarousel,
                    icons.productHighlightListing,
                    icons.productNaBolagsProfil,
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
                    icons.qasa,
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
                    icons.refresh,
                    icons.remppatori,
                    icons.road,
                    icons.rocket,
                    icons.roomService,
                    icons.rotateLeft,
                    icons.rotateRight,
                    icons.rowingBoat,
                    icons.ruler
                )
            )
        }
    }

    @Test
    fun warp_icons_S() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.sauna,
                    icons.scrollUp,
                    icons.search,
                    icons.seat,
                    icons.send,
                    icons.wrench,
                    icons.share,
                    icons.shipping,
                    icons.shirt,
                    icons.shoes,
                    icons.shoppingCart,
                    icons.shower,
                    icons.shrink,
                    icons.sidebarChevronLeft,
                    icons.sidebarChevronRight,
                    icons.singleCylinder,
                    icons.skyscraper,
                    icons.smileyGood,
                    icons.smileyHappy,
                    icons.smileyNeutral,
                    icons.smileySad,
                    icons.sofa,
                    icons.sorting,
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
                    icons.suitcaseTrolley,
                    icons.sun,
                    icons.support,
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
                    icons.ticket,
                    icons.townhouse,
                    icons.tractor,
                    icons.tree,
                    icons.trendDown,
                    icons.trendFlat,
                    icons.trendUp,
                    icons.tshirt,
                    icons.tv,
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
                    icons.washingMachine,
                    icons.waterPitcher,
                    icons.weight,
                    icons.wheelchair,
                    icons.wifi,
                    icons.wrench
                )
            )
        }
    }

    @Test
    fun warp_icons_X() {
        paparazzi.snapshot {
            WarpIconsAllSizes(
                listOf(
                    icons.x
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

    @Test
    fun warp_taxonomy_icons() {
        paparazzi.snapshot {
            WarpIconsAllSizes(icons.taxonomyIconList.filter { it is WarpTaxonomyIconResource })
        }
    }

    @Test
    fun warp_brand_icons() {
        paparazzi.snapshot {
            WarpIconsAllSizes(icons.taxonomyIconList.filter { it is WarpBrandIconResource })
        }
    }

    @Test
    fun warp_icon_by_identifier() {
        paparazzi.snapshot {
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
                    WarpIcon(identifier = "lotusflower", color = colors.icon.secondary)
                    WarpIcon(identifier = "sofa", color = colors.icon.secondary)
                    WarpIcon(identifier = "oikotie", color = colors.icon.secondary)
                }
            }
        }
    }

    @Test
    fun warp_icon_by_name() {
        paparazzi.snapshot {
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
                    WarpIcon(identifier = "lotusflower", color = colors.icon.secondary)
                    WarpIcon(identifier = "animalpaw", color = colors.icon.secondary)
                    WarpIcon(identifier = "house", color = colors.icon.secondary)
                }
            }
        }
    }

    @Composable
    private fun WarpIconsAllSizes(icons: List<WarpIconResource>) {
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
                        icons.forEach { CreateIcon(it, dimensions.icon.small) }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(dimensions.space2),
                        modifier = Modifier
                            .padding(dimensions.space2)
                    ) {
                        icons.forEach { CreateIcon(it, dimensions.icon.default) }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(dimensions.space2),
                        modifier = Modifier
                            .padding(dimensions.space2)
                    ) {
                        icons.forEach { CreateIcon(it, dimensions.icon.large) }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(dimensions.space2),
                        modifier = Modifier
                            .padding(dimensions.space2)
                    ) {
                        icons.forEach { CreateIcon(it, 28.dp) }
                    }
                }
            }
        }

    }

    @Composable
    private fun CreateIcon(icon: WarpIconResource, size: Dp) {
        WarpIcon(
            icon = icon,
            color = colors.icon.primary,
            size = size
        )
    }
}
