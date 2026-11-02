package com.schibsted.nmp.warp.components.utils

/**
 * Marks a UI where another Warp component may fit its use better. The UI works as it is; the
 * owning team decides whether to change it. A marker only: it has no effect at runtime or on
 * compilation, and is meant to be found by searching for it.
 *
 * @param component The Warp component that may fit better.
 * @param reason Why, in a sentence, e.g. which guideline the use matches.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class WarpComponentSuggestion(val component: SuggestedWarpComponent, val reason: String)

/** The Warp components [WarpComponentSuggestion] can point to. */
enum class SuggestedWarpComponent {
    /** `WarpDialog`, e.g. for confirming a destructive action. */
    Dialog,
}
