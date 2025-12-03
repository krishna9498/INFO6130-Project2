/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.espresso.BasicSample

import androidx.test.ext.junit.rules.activityScenarioRule
import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * The kotlin equivalent to the ChangeTextBehaviorTest, that
 * showcases simple view matchers and actions like [ViewMatchers.withId],
 * [ViewActions.click] and [ViewActions.typeText], and ActivityScenarioRule
 *
 *
 * Note that there is no need to tell Espresso that a view is in a different [Activity].
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorKtTest {
    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test before each test,
     * and close it after each test. This is a replacement for
     * [androidx.test.rule.ActivityTestRule].
     */
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun changeText_sameActivity() {

        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput))
            .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)))
    }

    @Test
    fun changeText_newActivity() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput)).perform(
            typeText(STRING_TO_BE_TYPED),
            closeSoftKeyboard()
        )
        onView(withId(R.id.activityChangeTextBtn)).perform(click())

        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.show_text_view)).check(matches(withText(STRING_TO_BE_TYPED)))
    }

    @Test
    fun validateTextViewInActivity() {
        onView(withId(R.id.textToBeChanged)).check(matches(withText(R.string.hello_world)))
        pause()
    }

    @Test
    fun validateEditTextChangeTextButton() {
        val inputText = "123"
        onView(withId(R.id.editTextUserInput)).perform(
            ViewActions.clearText(),
            ViewActions.typeText(inputText)
        )
        onView(withId(R.id.changeTextBt)).perform(ViewActions.click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText(inputText)))
        pause()
    }

    @Test
    fun validateEditTextOpenActivityChangeTextButton() {
        val inputText = "123"
        onView(withId(R.id.editTextUserInput)).perform(
            ViewActions.clearText(),
            ViewActions.typeText(inputText)
        )
        onView(withId(R.id.activityChangeTextBtn)).perform(ViewActions.click())
        onView(withId(R.id.show_text_view)).check(matches(withText(inputText)))
        pause()
    }

    @Test
    fun validateEmptyEditTextChangeTextButton() {
        onView(withId(R.id.changeTextBt)).perform(ViewActions.click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText("")))
        pause()
    }

    @Test
    fun validateEmptyEditTextOpenActivityChangeTextButton() {
        onView(withId(R.id.activityChangeTextBtn)).perform(ViewActions.click())
        onView(withId(R.id.show_text_view)).check(matches(withText("")))
        pause()
    }

    @Test
    fun validateNonEmptyEditTextChangeTextButton() {
        val inputText = "abcdef"
        onView(withId(R.id.editTextUserInput)).perform(
            ViewActions.clearText(),
            ViewActions.typeText(inputText)
        )
        onView(withId(R.id.changeTextBt)).perform(ViewActions.click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText(inputText)))
        pause()
    }

    @Test
    fun validateNonEmptyEditTextOpenActivityChangeTextButton() {
        val inputText = "abcdef"
        onView(withId(R.id.editTextUserInput)).perform(
            ViewActions.clearText(),
            ViewActions.typeText(inputText)
        )
        onView(withId(R.id.activityChangeTextBtn)).perform(ViewActions.click())
        onView(withId(R.id.show_text_view)).check(matches(withText(inputText)))
        pause()
    }

    private fun pause() {
        try {
            Thread.sleep(1500) // Pause for 1 second
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    companion object {
        val STRING_TO_BE_TYPED = "Espresso"
    }
}