package com.schibsted.nmp.warp.components

/**
 * Marks Warp APIs that support patterns the design guidelines advise against. Each one names the
 * preferred alternative in its docs; opt in only when that isn't an option.
 */
@RequiresOptIn(message = "This supports a pattern the design guidelines advise against. See its docs for the preferred alternative.")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class DiscouragedWarpApi
