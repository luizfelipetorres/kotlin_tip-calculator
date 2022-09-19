package com.lftf.tiptime

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {
        val costOfServiceText: ViewInteraction = Espresso.onView(ViewMatchers.withId(R.id.cost_of_service_edit_text))
        val calculateButton: ViewInteraction = Espresso.onView(withId(R.id.calculate_button))
        val tipResult: ViewInteraction = Espresso.onView(withId(R.id.tip_result))
        costOfServiceText.perform(ViewActions.typeText("50.00"))
        calculateButton.perform(ViewActions.click())
        tipResult.check(ViewAssertions.matches(withText("Tip amount: $10.00")))
    }
}