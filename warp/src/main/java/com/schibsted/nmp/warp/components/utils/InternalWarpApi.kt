package com.schibsted.nmp.warp.components.utils

/**
 * Marks Warp APIs meant for snapshot tests, previews and tooling, e.g. forced visual states or
 * static renderings of components that normally live in a popup or sheet.
 * They ship in the public artifact but are not part of the supported API surface.
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "Internal Warp API for snapshots, previews and tooling."
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
annotation class InternalWarpApi
