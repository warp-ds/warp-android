package com.schibsted.nmp.warp.theme

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.schibsted.nmp.warp.R

/**
 * A collection of Warp icons. Default size 32x32dp.
 */
open class WarpIconResource(
    open val vector: ImageVector,
    open val description: String
)

/**
 * A collection of Warp taxonomy icons.
 * Can be retrieved by unique identifier which is identical across all platforms.
 */

class WarpTaxonomyIconResource(
    override val vector: ImageVector,
    override val description: String,
    val identifier: String
) : WarpIconResource(
    vector = vector,
    description = description
)

class WarpBrandIconResource(
    override val vector: ImageVector,
    override val description: String,
    val identifier: String
) : WarpIconResource(
    vector = vector,
    description = description
)

object WarpIconResources {

    @Composable
    fun getIconByIdentifier(identifier: String): WarpIconResource? {
        return taxonomyIconList.find { it is WarpTaxonomyIconResource && it.identifier == identifier || it is WarpBrandIconResource && it.identifier == identifier }
    }

    @SuppressLint("DiscouragedApi")
    @Composable
    fun getIconByName(resourceName: String): WarpIconResource? {
        val context = LocalContext.current
        val drawableResourceId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)
        val stringResourceId = context.resources.getIdentifier(resourceName, "string", context.packageName)

        return if (drawableResourceId != 0) {
            WarpIconResource(
                ImageVector.vectorResource(id = drawableResourceId),
                stringResource(stringResourceId)
            )
        } else {
            null
        }
    }

    val taxonomyIconList: List<WarpIconResource>
        @Composable
        get() = listOf(
            airplane,
            airplaneHotel,
            autovex,
            babyOnesie,
            bulldozer,
            cabin,
            camping,
            carFront,
            carPart,
            carRent,
            carSubscription,
            cart,
            chainsaw,
            chair,
            dating,
            economy,
            football,
            geometricShapes,
            guitarBat,
            honk,
            hotel,
            iceSkater,
            job,
            minivan,
            mittAnbud,
            motorcycle,
            nettbil,
            oikotie,
            paintRoller,
            paw,
            phoneBadgeCheck,
            realEstate,
            remppatori,
            sailboat,
            shirt,
            smartphone,
            sofa,
            storeFront,
            stroller,
            sweater,
            tools,
            tractor,
            van,
            vase
        )

    val activeAds: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.activeads),
            stringResource(R.string.activeads)
        )

    val ads: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.ads),
            stringResource(R.string.ads)
        )

    val airCon: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.aircon),
            stringResource(R.string.aircon)
        )

    val airplane: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.airplane),
            stringResource(R.string.airplane),
            identifier = "airplane"
        )

    val airplaneHotel: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.airplanehotel),
            stringResource(R.string.airplanehotel),
            identifier = "airplane-hotel"
        )

    val alert: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.alert),
            stringResource(R.string.alert)
        )

    val allWheelDrive: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.allwheeldrive),
            stringResource(R.string.allwheeldrive)
        )

    val animalPaw: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.animalpaw),
            stringResource(R.string.animalpaw)
        )

    val archway: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.archway),
            stringResource(R.string.archway)
        )

    val arrowDown: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.arrowdown),
            stringResource(R.string.arrowdown)
        )

    val arrowLeft: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.arrowleft),
            stringResource(R.string.arrowleft)
        )

    val arrowRight: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.arrowright),
            stringResource(R.string.arrowright)
        )

    val arrowUp: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.arrowup),
            stringResource(R.string.arrowup)
        )

    val attachement: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.attachment),
            stringResource(R.string.attachment)
        )

    val automatic: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.automatic),
            stringResource(R.string.automatic)
        )

    val autovex: WarpBrandIconResource
        @Composable
        get() = WarpBrandIconResource(
            ImageVector.vectorResource(id = R.drawable.autovex_),
            stringResource(R.string.autovex),
            identifier = "autovex"
        )

    val awardMedal: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.awardmedal),
            stringResource(R.string.awardmedal)
        )

    val babyOnesie: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.babyonesie),
            stringResource(R.string.babyonesie),
            identifier = "baby-onesie"
        )

    val backWheelDrive: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.backwheeldrive),
            stringResource(R.string.backwheeldrive)
        )

    val bag: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.bag),
            stringResource(R.string.bag)
        )

    val bank: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.bank),
            stringResource(R.string.bank)
        )

    val bankIdNo: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.bankidno),
            stringResource(R.string.bankidno)
        )

    val batteryEmpty: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.batteryempty),
            stringResource(R.string.batteryempty)
        )

    val batteryFull: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.batteryfull),
            stringResource(R.string.batteryfull)
        )

    val batteryHalfFull: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.batteryhalffull),
            stringResource(R.string.batteryhalffull)
        )

    val beach: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.beach),
            stringResource(R.string.beach)
        )

    val bell: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.bell),
            stringResource(R.string.bell)
        )

    val bellFilled: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.bellfilled),
            stringResource(R.string.bellfilled)
        )

    val bin: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.bin),
            stringResource(R.string.bin)
        )

    val block: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.block),
            stringResource(R.string.block)
        )

    val boatLength: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.boatlength),
            stringResource(R.string.boatlength)
        )

    val bolt: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.bolt),
            stringResource(R.string.bolt)
        )

    val bookmark: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.bookmark),
            stringResource(R.string.bookmark)
        )

    val browser: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.browser),
            stringResource(R.string.browser)
        )

    val browserVerified: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.browserverified),
            stringResource(R.string.browserverified)
        )

    val building: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.building),
            stringResource(R.string.building)
        )

    val buildingPlot: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.buildingplot),
            stringResource(R.string.buildingplot)
        )

    val bulb: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.bulb),
            stringResource(R.string.bulb)
        )

    val bulldozer: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.bulldozer),
            stringResource(R.string.bulldozer),
            identifier = "bulldozer"
        )

    val burger: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.burger),
            stringResource(R.string.burger)
        )

    val bus: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.bus),
            stringResource(R.string.bus)
        )

    val cabin: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.cabin),
            stringResource(R.string.cabin),
            identifier = "cabin"
        )

    val cabinHut: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.cabinhut),
            stringResource(R.string.cabinhut)
        )

    val calculator: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.calculator),
            stringResource(R.string.calculator)
        )

    val calendar: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.calendar),
            stringResource(R.string.calendar)
        )

    val camera: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.camera),
            stringResource(R.string.camera)
        )

    val camping: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.camping),
            stringResource(R.string.camping),
            identifier = "camping"
        )

    val cancel: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.cancel),
            stringResource(R.string.cancel)
        )

    val car: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.car),
            stringResource(R.string.car)
        )

    val carFront: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.carfront),
            stringResource(R.string.carfront),
            identifier = "car-front"
        )

    val carKey: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.carkey),
            stringResource(R.string.carkey)
        )

    val carPart: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.carpart),
            stringResource(R.string.carpart),
            identifier = "car-part"
        )

    val carRent: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.carrent),
            stringResource(R.string.carrent),
            identifier = "car-rent"
        )

    val carService: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.carservice),
            stringResource(R.string.carservice)
        )

    val carSubscription: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.carsubscription),
            stringResource(R.string.carsubscription),
            identifier = "car-subscription"
        )

    val cart: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.cart),
            stringResource(R.string.cart),
            identifier = "cart"
        )

    val chainsaw: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.chainsaw),
            stringResource(R.string.chainsaw),
            identifier = "chainsaw"
        )

    val chair: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.chair),
            stringResource(R.string.chair),
            identifier = "chair"
        )

    val charger: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.charger),
            stringResource(R.string.charger)
        )

    val charter: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.charter),
            stringResource(R.string.charter)
        )

    val chatRequest: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.chatrequest),
            stringResource(R.string.chatrequest)
        )

    val chatSupport: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.chatsupport),
            stringResource(R.string.chatsupport)
        )

    val check: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.check),
            stringResource(R.string.check)
        )

    val checkList: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.checklist),
            stringResource(R.string.checklist)
        )

    val checkShield: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.checkshield),
            stringResource(R.string.checkshield)
        )

    val chevronDoubleLeft: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.chevrondoubleleft),
            stringResource(R.string.chevrondoubleleft)
        )

    val chevronDoubleRight: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.chevrondoubleright),
            stringResource(R.string.chevrondoubleright)
        )

    val chevronDown: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.chevrondown),
            stringResource(R.string.chevrondown)
        )

    val chevronLeft: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.chevronleft),
            stringResource(R.string.chevronleft)
        )

    val chevronRight: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.chevronright),
            stringResource(R.string.chevronright)
        )

    val chevronUp: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.chevronup),
            stringResource(R.string.chevronup)
        )

    val circlePlus: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.circleplus),
            stringResource(R.string.circleplus)
        )

    val circlePlusFilled: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.circleplusfilled),
            stringResource(R.string.circleplusfilled)
        )

    val circleUser: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.circleuser),
            stringResource(R.string.circleuser)
        )

    val circleUserFilled: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.circleuserfilled),
            stringResource(R.string.circleuserfilled)
        )

    val clock: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.clock),
            stringResource(R.string.clock)
        )

    val close: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.close),
            stringResource(R.string.close)
        )

    val cog: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.cog),
            stringResource(R.string.cog)
        )

    val colorPalette: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.colorpalette),
            stringResource(R.string.colorpalette)
        )

    val cottagePlot: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.cottageplot),
            stringResource(R.string.cottageplot)
        )

    val creditCard: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.creditcard),
            stringResource(R.string.creditcard)
        )

    val cursor: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.cursor),
            stringResource(R.string.cursor)
        )

    val dating: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.dating),
            stringResource(R.string.dating),
            identifier = "dating"
        )

    val delivery: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.delivery),
            stringResource(R.string.delivery)
        )

    val diner: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.diner),
            stringResource(R.string.diner)
        )

    val discount: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.discount),
            stringResource(R.string.discount)
        )

    val dislike: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.dislike),
            stringResource(R.string.dislike)
        )

    val door: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.door),
            stringResource(R.string.door)
        )

    val dots: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.dots),
            stringResource(R.string.dots)
        )

    val dotsVertical: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.dotsvertical),
            stringResource(R.string.dotsvertical)
        )

    val doubleBed: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.doublebed),
            stringResource(R.string.doublebed)
        )

    val download: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.download),
            stringResource(R.string.download)
        )

    val drawer: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.drawer),
            stringResource(R.string.drawer)
        )

    val drink: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.drink),
            stringResource(R.string.drink)
        )

    val economy: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.economy),
            stringResource(R.string.economy),
            identifier = "economy"
        )

    val edit: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.edit),
            stringResource(R.string.edit)
        )

    val energy: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.energy),
            stringResource(R.string.energy)
        )

    val engine: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.engine),
            stringResource(R.string.engine)
        )

    val engineBelt: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.enginebelt),
            stringResource(R.string.enginebelt)
        )

    val error: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.error),
            stringResource(R.string.error)
        )

    val exchange: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.exchange),
            stringResource(R.string.exchange)
        )

    val expand: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.expand),
            stringResource(R.string.expand)
        )

    val eyeOff: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.eyeoff),
            stringResource(R.string.eyeoff)
        )

    val eyeOn: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.eyeon),
            stringResource(R.string.eyeon)
        )

    val facebook: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.facebook),
            stringResource(R.string.facebook)
        )

    val farm: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.farm),
            stringResource(R.string.farm)
        )

    val feedback: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.feedback),
            stringResource(R.string.feedback)
        )

    val fileAdd: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.fileadd),
            stringResource(R.string.fileadd)
        )

    val filter: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.filter),
            stringResource(R.string.filter)
        )

    val fireplace: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.fireplace),
            stringResource(R.string.fireplace)
        )

    val fishing: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.fishing),
            stringResource(R.string.fishing)
        )

    val fitness: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.fitness),
            stringResource(R.string.fitness)
        )

    val football: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.football),
            stringResource(R.string.football),
            identifier = "football"
        )

    val frontWheelDrive: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.frontwheeldrive),
            stringResource(R.string.frontwheeldrive)
        )

    val gasDiesel: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.gasdiesel),
            stringResource(R.string.gasdiesel)
        )

    val gasFuel: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.gasfuel),
            stringResource(R.string.gasfuel)
        )

    val gasHybrid: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.gashybrid),
            stringResource(R.string.gashybrid)
        )

    val geometricShapes: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.geometricshapes),
            stringResource(R.string.geometricshapes),
            identifier = "geometric-shapes"
        )

    val graphLine: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.graphline),
            stringResource(R.string.graphline)
        )

    val graphPie: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.graphpie),
            stringResource(R.string.graphpie)
        )

    val grid: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.grid),
            stringResource(R.string.grid)
        )

    val grill: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.grill),
            stringResource(R.string.grill)
        )

    val guitarBat: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.guitarbat),
            stringResource(R.string.guitarbat),
            identifier = "guitar-bat"
        )

    val headset: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.headset),
            stringResource(R.string.headset)
        )

    val heart: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.heart),
            stringResource(R.string.heart)
        )

    val heartRate: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.heartrate),
            stringResource(R.string.heartrate)
        )

    val hiking: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.hiking),
            stringResource(R.string.hiking)
        )

    val history: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.history),
            stringResource(R.string.history)
        )

    val honk: WarpBrandIconResource
        @Composable
        get() = WarpBrandIconResource(
            ImageVector.vectorResource(id = R.drawable.honk_),
            stringResource(R.string.honk),
            identifier = "honk"
        )

    val hotel: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.hotel),
            stringResource(R.string.hotel),
            identifier = "hotel"
        )

    val house: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.house),
            stringResource(R.string.house)
        )

    val houseBed: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.housebed),
            stringResource(R.string.housebed)
        )

    val houseCabin: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.housecabin),
            stringResource(R.string.housecabin)
        )

    val houseFilled: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.housefilled),
            stringResource(R.string.housefilled)
        )

    val houseModern: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.housemodern),
            stringResource(R.string.housemodern)
        )

    val housePerson: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.houseperson),
            stringResource(R.string.houseperson)
        )

    val iceSkater: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.iceskater),
            stringResource(R.string.iceskater),
            identifier = "ice-skater"
        )

    val image: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.image),
            stringResource(R.string.image)
        )

    val info: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.info),
            stringResource(R.string.info)
        )

    val instagram: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.instagram),
            stringResource(R.string.instagram)
        )

    val job: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.job),
            stringResource(R.string.job),
            identifier = "job"
        )

    val keys: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.keys),
            stringResource(R.string.keys)
        )

    val krone: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.krone),
            stringResource(R.string.krone)
        )

    val lamp: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.lamp),
            stringResource(R.string.lamp)
        )

    val landscape: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.landscape),
            stringResource(R.string.landscape)
        )

    val laundry: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.laundry),
            stringResource(R.string.laundry)
        )

    val layers: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.layers),
            stringResource(R.string.layers)
        )

    val leaf: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.leaf),
            stringResource(R.string.leaf)
        )

    val lift: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.lift),
            stringResource(R.string.lift)
        )

    val like: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.like),
            stringResource(R.string.like)
        )

    val link: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.link),
            stringResource(R.string.link)
        )

    val linkExternal: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.linkexternal),
            stringResource(R.string.linkexternal)
        )

    val listSort: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.listsort),
            stringResource(R.string.listsort)
        )

    val lockShield: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.lockshield),
            stringResource(R.string.lockshield)
        )

    val logout: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.logout),
            stringResource(R.string.logout)
        )

    val mail: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.mail),
            stringResource(R.string.mail)
        )

    val mailbox: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.mailbox),
            stringResource(R.string.mailbox)
        )

    val manual: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.manual),
            stringResource(R.string.manual)
        )

    val map: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.map),
            stringResource(R.string.map)
        )

    val measure: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.measure),
            stringResource(R.string.measure)
        )

    val message: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.message),
            stringResource(R.string.message)
        )

    val messages: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.messages),
            stringResource(R.string.messages)
        )

    val messagesFilled: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.messagesfilled),
            stringResource(R.string.messagesfilled)
        )

    val minivan: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.minivan),
            stringResource(R.string.minivan),
            identifier = "minivan"
        )

    val minus: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.minus),
            stringResource(R.string.minus)
        )

    val mittAnbud: WarpBrandIconResource
        @Composable
        get() = WarpBrandIconResource(
            ImageVector.vectorResource(id = R.drawable.mitt_anbud),
            stringResource(R.string.mittanbud),
            identifier = "mitt-anbud"
        )

    val mixer: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.mixer),
            stringResource(R.string.mixer)
        )

    val money: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.money),
            stringResource(R.string.money)
        )

    val motorcycle: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.motorcycle),
            stringResource(R.string.motorcycle),
            identifier = "motorcycle"
        )

    val mountain: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.mountain),
            stringResource(R.string.mountain)
        )

    val nettbil: WarpBrandIconResource
        @Composable
        get() = WarpBrandIconResource(
            ImageVector.vectorResource(id = R.drawable.nett_bil),
            stringResource(R.string.nettbil),
            identifier = "nettbil"
        )

    val newAd: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.newad),
            stringResource(R.string.newad)
        )

    val norwegianMotor: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.norwegianmotor),
            stringResource(R.string.norwegianmotor)
        )

    val noSmoking: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.nosmoking),
            stringResource(R.string.nosmoking)
        )

    val officeDesk: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.officedesk),
            stringResource(R.string.officedesk)
        )

    val oikotie: WarpBrandIconResource
        @Composable
        get() = WarpBrandIconResource(
            ImageVector.vectorResource(id = R.drawable.oikotie_),
            stringResource(R.string.oikotie),
            identifier = "oikotie"
        )

    val organize: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.organize),
            stringResource(R.string.organize)
        )

    val paintRoller: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.paintroller),
            stringResource(R.string.paintroller),
            identifier = "paint-roller"
        )

    val parking: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.parking),
            stringResource(R.string.parking)
        )

    val paw: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.paw),
            stringResource(R.string.paw),
            identifier = "paw"
        )

    val phone: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.phone),
            stringResource(R.string.phone)
        )

    val phoneBadgeCheck: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.phonebadgecheck),
            stringResource(R.string.phonebadgecheck),
            identifier = "phone-badge-check"
        )

    val phoneScratched: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.phonescratched),
            stringResource(R.string.phonescratched)
        )

    val phoneUsed: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.phoneused),
            stringResource(R.string.phoneused)
        )

    val pictureStack: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.picturestack),
            stringResource(R.string.picturestack)
        )

    val pinMarker: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.pinmarker),
            stringResource(R.string.pinmarker)
        )

    val pinRound: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.pinround),
            stringResource(R.string.pinround)
        )

    val planeTicket: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.planeticket),
            stringResource(R.string.planeticket)
        )

    val play: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.play),
            stringResource(R.string.play)
        )

    val playhouse: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.playhouse),
            stringResource(R.string.playhouse)
        )

    val plots: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.plots),
            stringResource(R.string.plots)
        )

    val plus: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.plus),
            stringResource(R.string.plus)
        )

    val productBlink: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.productblink),
            stringResource(R.string.productblink)
        )

    val productBump: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.productbump),
            stringResource(R.string.productbump)
        )

    val productCarousel: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.productcarousel),
            stringResource(R.string.productcarousel)
        )

    val productHighlightListing: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.producthighlightlisting),
            stringResource(R.string.producthighlightlisting)
        )

    val productNaBolagsProfil: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.productnabolagsprofil),
            stringResource(R.string.productnabolagsprofil)
        )

    val productNoAds: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.productnoads),
            stringResource(R.string.productnoads)
        )

    val productStarred: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.productstarred),
            stringResource(R.string.productstarred)
        )

    val productTop: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.producttop),
            stringResource(R.string.producttop)
        )

    val propeller: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.propeller),
            stringResource(R.string.propeller)
        )

    val question: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.question),
            stringResource(R.string.question)
        )

    val realEstate: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.realestate),
            stringResource(R.string.realestate),
            identifier = "real-estate"
        )

    val refresh: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.refresh),
            stringResource(R.string.refresh)
        )

    val remppatori: WarpBrandIconResource
        @Composable
        get() = WarpBrandIconResource(
            ImageVector.vectorResource(id = R.drawable.remppa_tori),
            stringResource(R.string.remppatori),
            identifier = "remppatori"
        )

    val roomService: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.roomservice),
            stringResource(R.string.roomservice)
        )

    val sailboat: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.sailboat),
            stringResource(R.string.sailboat),
            identifier = "sailboat"
        )

    val sailing: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.sailing),
            stringResource(R.string.sailing)
        )

    val sauna: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.sauna),
            stringResource(R.string.sauna)
        )

    val scrollUp: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.scrollup),
            stringResource(R.string.scrollup)
        )

    val search: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.search),
            stringResource(R.string.search)
        )

    val searchFavorites: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.searchfavorites),
            stringResource(R.string.searchfavorites)
        )

    val seat: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.seat),
            stringResource(R.string.seat)
        )

    val send: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.send),
            stringResource(R.string.send)
        )

    val service: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.service),
            stringResource(R.string.service)
        )

    val share: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.share),
            stringResource(R.string.share)
        )

    val shipping: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.shipping),
            stringResource(R.string.shipping)
        )

    val shirt: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.shirt),
            stringResource(R.string.shirt),
            identifier = "shirt"
        )

    val shoes: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.shoes),
            stringResource(R.string.shoes)
        )

    val shoppingCart: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.shoppingcart),
            stringResource(R.string.shoppingcart)
        )

    val shower: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.shower),
            stringResource(R.string.shower)
        )

    val skyscraper: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.skyscraper),
            stringResource(R.string.skyscraper)
        )

    val smartphone: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.smartphone),
            stringResource(R.string.smartphone),
            identifier = "smartphone"
        )

    val smileyGood: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.smileygood),
            stringResource(R.string.smileygood)
        )

    val smileyHappy: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.smileyhappy),
            stringResource(R.string.smileyhappy)
        )

    val smileyNeutral: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.smileyneutral),
            stringResource(R.string.smileyneutral)
        )

    val smileySad: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.smileysad),
            stringResource(R.string.smileysad)
        )

    val sofa: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.sofa),
            stringResource(R.string.sofa),
            identifier = "sofa"
        )

    val sorting: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.sorting),
            stringResource(R.string.sorting)
        )

    val spa: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.spa),
            stringResource(R.string.spa)
        )

    val sparkles: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.sparkles),
            stringResource(R.string.sparkles)
        )

    val speedometer: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.speedometer),
            stringResource(R.string.speedometer)
        )

    val stairs: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.stairs),
            stringResource(R.string.stairs)
        )

    val starCheck: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.starcheck),
            stringResource(R.string.starcheck)
        )

    val starEmpty: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.starempty),
            stringResource(R.string.starempty)
        )

    val starFull: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.starfull),
            stringResource(R.string.starfull)
        )

    val starHalf: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.starhalf),
            stringResource(R.string.starhalf)
        )

    val store: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.store),
            stringResource(R.string.store)
        )

    val storeFront: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.storefront),
            stringResource(R.string.storefront),
            identifier = "store-front"
        )

    val stove: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.stove),
            stringResource(R.string.stove)
        )

    val stroller: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.stroller),
            stringResource(R.string.stroller),
            identifier = "stroller"
        )

    val success: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.success),
            stringResource(R.string.success)
        )

    val suitcase: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.suitcase),
            stringResource(R.string.suitcase)
        )

    val support: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.support),
            stringResource(R.string.support)
        )

    val sweater: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.sweater),
            stringResource(R.string.sweater),
            identifier = "sweater"
        )

    val swimming: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.swimming),
            stringResource(R.string.swimming)
        )

    val tag: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.tag),
            stringResource(R.string.tag)
        )

    val taskList: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.tasklist),
            stringResource(R.string.tasklist)
        )

    val textBold: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.textbold),
            stringResource(R.string.textbold)
        )

    val textItalic: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.textitalic),
            stringResource(R.string.textitalic)
        )

    val textStrikethrough: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.textstrikethrough),
            stringResource(R.string.textstrikethrough)
        )

    val textUnderline: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.textunderline),
            stringResource(R.string.textunderline)
        )

    val theater: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.theater),
            stringResource(R.string.theater)
        )

    val threeSixty: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.threesixty),
            stringResource(R.string.threesixty)
        )

    val tools: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.tools),
            stringResource(R.string.tools),
            identifier = "tools"
        )

    val townhouse: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.townhouse),
            stringResource(R.string.townhouse)
        )

    val tractor: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.tractor),
            stringResource(R.string.tractor),
            identifier = "tractor"
        )

    val trendDown: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.trenddown),
            stringResource(R.string.trenddown)
        )

    val trendFlat: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.trendflat),
            stringResource(R.string.trendflat)
        )

    val trendUp: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.trendup),
            stringResource(R.string.trendup)
        )

    val tv: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.tv),
            stringResource(R.string.tv)
        )

    val twitter: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.twitter),
            stringResource(R.string.twitter)
        )

    val up: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.up),
            stringResource(R.string.up)
        )

    val upload: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.upload),
            stringResource(R.string.upload)
        )

    val user: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.user),
            stringResource(R.string.user)
        )

    val userGroup: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.usergroup),
            stringResource(R.string.usergroup)
        )

    val users: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.users),
            stringResource(R.string.users)
        )

    val userWoman: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.userwoman),
            stringResource(R.string.userwoman)
        )

    val vacuum: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.vacuum),
            stringResource(R.string.vacuum)
        )

    val van: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.van),
            stringResource(R.string.van),
            identifier = "van"
        )

    val vase: WarpTaxonomyIconResource
        @Composable
        get() = WarpTaxonomyIconResource(
            ImageVector.vectorResource(id = R.drawable.vase),
            stringResource(R.string.vase),
            identifier = "vase"
        )

    val verification: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.verification),
            stringResource(R.string.verification)
        )

    val wallet: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.wallet),
            stringResource(R.string.wallet)
        )

    val warning: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.warning),
            stringResource(R.string.warning)
        )

    val warranty: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.warranty),
            stringResource(R.string.warranty)
        )

    val waterPitcher: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.waterpitcher),
            stringResource(R.string.waterpitcher)
        )

    val wheelchair: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.wheelchair),
            stringResource(R.string.wheelchair)
        )

    val wifi: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.wifi),
            stringResource(R.string.wifi)
        )

    val woods: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.woods),
            stringResource(R.string.woods)
        )

    val youtube: WarpIconResource
        @Composable
        get() = WarpIconResource(
            ImageVector.vectorResource(id = R.drawable.youtube),
            stringResource(R.string.youtube)
        )
}