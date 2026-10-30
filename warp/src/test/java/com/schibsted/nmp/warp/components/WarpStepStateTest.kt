package com.schibsted.nmp.warp.components

import com.schibsted.nmp.warp.components.WarpStepState.Complete
import com.schibsted.nmp.warp.components.WarpStepState.Error
import com.schibsted.nmp.warp.components.WarpStepState.InProgress
import com.schibsted.nmp.warp.components.WarpStepState.Incomplete
import org.junit.Assert.assertEquals
import org.junit.Test

class WarpStepStateTest {

    @Test
    fun linearStepStates_completesStepsBeforeTheActiveStep() {
        assertEquals(
            listOf(Complete, InProgress, Incomplete),
            linearStepStates(steps = 3, activeStep = 1)
        )
    }

    @Test
    fun linearStepStates_pastTheLastStep_completesAllSteps() {
        assertEquals(
            listOf(Complete, Complete, Complete),
            linearStepStates(steps = 3, activeStep = 3)
        )
    }

    @Test
    fun progress_isTheInProgressStep() {
        assertEquals(1, listOf(Complete, InProgress, Incomplete).progress())
    }

    @Test
    fun progress_stopsAtAnError() {
        assertEquals(1, listOf(Complete, Error, Incomplete).progress())
    }

    @Test
    fun progress_stopsAtTheFirstStepThatIsNotComplete() {
        assertEquals(1, listOf(Complete, Error, InProgress, Complete).progress())
    }

    @Test
    fun progress_withAllStepsComplete_fillsEveryTrack() {
        assertEquals(3, listOf(Complete, Complete, Complete).progress())
    }

    @Test
    fun progress_withNoStepsStarted_isTheFirstStep() {
        assertEquals(0, listOf(Incomplete, Incomplete, Incomplete).progress())
    }
}
