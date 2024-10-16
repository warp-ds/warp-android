package com.schibsted.nmp.warp.theme

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.schibsted.nmp.warp.R

object WarpResources {
    val icons: WarpIcons
        @Composable
        get() = WarpIcons
}

object WarpIcons {

    @SuppressLint("DiscouragedApi")
    @Composable
    fun getIconByName(context: Context, resourceName: String): ImageVector? {
        val resourceId =
            context.resources.getIdentifier(resourceName, "drawable", context.packageName)
        return if (resourceId != 0) {
            ImageVector.vectorResource(id = resourceId)
        } else {
            null
        }
    }

    val activeAds: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.activeads)

    val ads: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ads)

    val airCon: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.aircon)

    val airplane: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.airplane)

    val airplaneHotel: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.airplanehotel)

    val alert: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.alert)

    val allWheelDrive: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.allwheeldrive)

    val animalPaw: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.animalpaw)

    val archway: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.archway)

    val arrowDown: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.arrowdown)

    val arrowLeft: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.arrowleft)

    val arrowRight: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.arrowright)

    val arrowUp: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.arrowup)

    val attachement: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.attachment)

    val automatic: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.automatic)

    val autovex: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.autovex)

    val babyOnesie: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.babyonesie)

    val backWheelDrive: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.backwheeldrive)

    val bag: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.bag)

    val bank: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.bank)

    val bankIdNo: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.bankidno)

    val batteryEmpty: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.batteryempty)

    val batteryFull: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.batteryfull)

    val batteryHalfFull: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.batteryhalffull)

    val beach: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.beach)

    val bell: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.bell)

    val bellFilled: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.bellfilled)

    val bin: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.bin)

    val block: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.block)

    val boatLength: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.boatlength)

    val bolt: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.bolt)

    val bookmark: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.bookmark)

    val browser: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.browser)

    val browserVerified: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.browserverified)

    val building: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.building)

    val buildingPlot: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.buildingplot)

    val bulb: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.bulb)

    val bulldozer: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.bulldozer)

    val burger: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.burger)

    val bus: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.bus)

    val cabin: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.cabin)

    val cabinHut: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.cabinhut)

    val calculator: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.calculator)

    val calendar: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.calendar)

    val camera: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.camera)

    val camping: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.camping)

    val cancel: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.cancel)

    val car: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.car)

    val carFront: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.carfront)

    val carKey: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.carkey)

    val carPart: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.carpart)

    val carRent: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.carrent)

    val carService: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.carservice)

    val carSubscription: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.carsubscription)

    val cart: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.cart)

    val chainsaw: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.chainsaw)

    val chair: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.chair)

    val charger: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.charger)

    val charter: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.charter)

    val chatRequest: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.chatrequest)

    val chatSupport: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.chatsupport)

    val check: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.check)

    val checkList: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.checklist)

    val checkShield: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.checkshield)

    val chevronDoubleLeft: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.chevrondoubleleft)

    val chevronDoubleRight: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.chevrondoubleright)

    val chevronDown: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.chevrondown)

    val chevronLeft: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.chevronleft)

    val chevronRight: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.chevronright)

    val chevronUp: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.chevronup)

    val circlePlus: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.circleplus)

    val circlePlusFilled: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.circleplusfilled)

    val circleUser: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.circleuser)

    val circleUserFilled: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.circleuserfilled)

    val clock: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.clock)

    val close: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.close)

    val cog: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.cog)

    val colorPalette: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.colorpalette)

    val cottagePlot: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.cottageplot)

    val creditCard: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.creditcard)

    val cursor: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.cursor)

    val dating: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.dating)

    val delivery: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.delivery)

    val diner: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.diner)

    val discount: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.discount)

    val dislike: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.dislike)

    val door: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.door)

    val dots: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.dots)

    val doubleBed: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.doublebed)

    val download: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.download)

    val drawer: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.drawer)

    val drink: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.drink)

    val economy: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.economy)

    val edit: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.edit)

    val energy: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.energy)

    val engine: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.engine)

    val engineBelt: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.enginebelt)

    val error: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.error)

    val exchange: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.exchange)

    val expand: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.expand)

    val eyeOff: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.eyeoff)

    val eyeOn: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.eyeon)

    val facebook: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.facebook)

    val farm: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.farm)

    val feedback: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.feedback)

    val fileAdd: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.fileadd)

    val filter: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.filter)

    val fireplace: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.fireplace)

    val fishing: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.fishing)

    val fitness: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.fitness)

    val football: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.football)

    val frontWheelDrive: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.frontwheeldrive)

    val gasDiesel: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.gasdiesel)

    val gasFuel: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.gasfuel)

    val gasHybrid: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.gashybrid)

    val geometricShapes: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.geometricshapes)

    val graphLine: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.graphline)

    val graphPie: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.graphpie)

    val grid: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.grid)

    val grill: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.grill)

    val guitarBat: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.guitarbat)

    val headset: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.headset)

    val heart: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.heart)

    val heartRate: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.heartrate)

    val hiking: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.hiking)

    val history: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.history)

    val honk: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.honk)

    val hotel: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.hotel)

    val house: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.house)

    val houseBed: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.housebed)

    val houseCabin: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.housecabin)

    val houseFilled: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.housefilled)

    val houseModern: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.housemodern)

    val housePerson: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.houseperson)

    val iceSkater: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.iceskater)

    val image: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.image)

    val info: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.info)

    val instagram: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.instagram)

    val job: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.job)

    val keys: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.keys)

    val krone: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.krone)

    val lamp: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.lamp)

    val landscape: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.landscape)

    val laundry: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.laundry)

    val layers: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.layers)

    val leaf: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.leaf)

    val lift: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.lift)

    val like: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.like)

    val link: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.link)

    val linkExternal: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.linkexternal)

    val listSort: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.listsort)

    val lockShield: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.lockshield)

    val logout: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.logout)

    val mail: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.mail)

    val mailbox: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.mailbox)

    val manual: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.manual)

    val map: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.map)

    val measure: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.measure)

    val message: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.message)

    val messages: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.messages)

    val messagesFilled: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.messagesfilled)

    val minivan: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.minivan)

    val minus: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.minus)

    val mittAnbud: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.mittanbud)

    val mixer: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.mixer)

    val money: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.money)

    val motorcycle: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.motorcycle)

    val mountain: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.mountain)

    val nettbil: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.nettbil)

    val newAd: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.newad)

    val norwegianMotor: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.norwegianmotor)

    val noSmoking: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.nosmoking)

    val officeDesk: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.officedesk)

    val oikotie: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.oikotie)

    val organize: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.organize)

    val paintRoller: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.paintroller)

    val parking: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.parking)

    val paw: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.paw)

    val phone: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.phone)

    val phoneBadgeCheck: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.phonebadgecheck)

    val phoneScratched: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.phonescratched)

    val phoneUsed: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.phoneused)

    val pictureStack: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.picturestack)

    val pinMarker: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.pinmarker)

    val pinRound: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.pinround)

    val planeTicket: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.planeticket)

    val play: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.play)

    val playhouse: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.playhouse)

    val plots: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.plots)

    val plus: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.plus)

    val productBlink: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.productblink)

    val productBump: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.productbump)

    val productCarousel: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.productcarousel)

    val productHighlightListing: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.producthighlightlisting)

    val productNaBolagsProfil: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.productnabolagsprofil)

    val productNoAds: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.productnoads)

    val productStarred: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.productstarred)

    val productTop: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.producttop)

    val propeller: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.propeller)

    val question: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.question)

    val realEstate: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.realestate)

    val refresh: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.refresh)

    val remppatori: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.remppatori)

    val roomService: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.roomservice)

    val sailboat: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.sailboat)

    val sailing: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.sailing)

    val sauna: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.sauna)

    val scrollUp: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.scrollup)

    val search: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.search)

    val searchFavorites: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.searchfavorites)

    val seat: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.seat)

    val send: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.send)

    val service: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.service)

    val share: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.share)

    val shipping: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.shipping)

    val shirt: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.shirt)

    val shoes: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.shoes)

    val shoppingCart: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.shoppingcart)

    val shower: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.shower)

    val skyscraper: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.skyscraper)

    val smartphone: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.smartphone)

    val smileyGood: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.smileygood)

    val smileyHappy: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.smileyhappy)

    val smileyNeutral: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.smileyneutral)

    val smileySad: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.smileysad)

    val sofa: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.sofa)

    val sorting: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.sorting)

    val spa: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.spa)

    val sparkles: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.sparkles)

    val speedometer: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.speedometer)

    val stairs: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.stairs)

    val starCheck: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.starcheck)

    val starEmpty: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.starempty)

    val starFull: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.starfull)

    val starHalf: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.starhalf)

    val store: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.store)

    val storeFront: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.storefront)

    val stove: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.stove)

    val stroller: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.stroller)

    val success: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.success)

    val suitcase: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.suitcase)

    val support: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.support)

    val sweater: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.sweater)

    val swimming: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.swimming)

    val tag: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.tag)

    val taskList: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.tasklist)

    val textBold: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.textbold)

    val textItalic: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.textitalic)

    val textStrikethrough: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.textstrikethrough)

    val textUnderline: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.textunderline)

    val theater: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.theater)

    val threeSixty: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.threesixty)

    val tools: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.tools)

    val townhouse: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.townhouse)

    val tractor: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.tractor)

    val trendDown: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.trenddown)

    val trendFlat: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.trendflat)

    val trendUp: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.trendup)

    val tv: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.tv)

    val twitter: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.twitter)

    val up: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.up)

    val upload: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.upload)

    val user: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.user)

    val userGroup: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.usergroup)

    val users: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.users)

    val userWoman: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.userwoman)

    val vacuum: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.vacuum)

    val van: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.van)

    val vase: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.vase)

    val verification: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.verification)

    val wallet: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.wallet)

    val warning: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.warning)

    val warranty: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.warranty)

    val wheelchair: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.wheelchair)

    val wifi: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.wifi)

    val woods: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.woods)

    val youtube: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.youtube)
}

internal val LocalResources = staticCompositionLocalOf { WarpResources }
