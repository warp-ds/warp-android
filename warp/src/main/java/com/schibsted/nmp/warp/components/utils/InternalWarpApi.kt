package com.schibsted.nmp.warp.components.utils

/**
 * Marks Warp APIs that exist only for snapshot tests and previews (e.g. forced visual states).
 * They ship in the public artifact but are not part of the supported API surface.
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "Internal Warp API, for snapshots/previews only."
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
annotation class InternalWarpApi
