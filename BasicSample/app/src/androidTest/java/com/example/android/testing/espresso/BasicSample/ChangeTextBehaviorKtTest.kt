package com.example.android.testing.espresso.BasicSample

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorKtTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Test 1: Validate correct strings in the TextView in the main activity
    @Test
    fun validateTextViewInMainActivity() {
        onView(withId(R.id.textToBeChanged)).check(matches(withText(R.string.hello_world)))
    }

    // Test 2: Enter "123" and press Change Text button, and test the string
    @Test
    fun enter123AndPressChangeTextButton() {
        val inputText = "123"
        onView(withId(R.id.editTextUserInput)).perform(
            ViewActions.clearText(),
            ViewActions.typeText(inputText)
        )
        onView(withId(R.id.changeTextBt)).perform(ViewActions.click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText(inputText)))
    }

    // Test 3: Enter "123" and press Open Activity and Change Text button, and test the string in ShowTextActivity
    @Test
    fun enter123AndOpenActivityChangeTextButton() {
        val inputText = "123"
        onView(withId(R.id.editTextUserInput)).perform(
            ViewActions.clearText(),
            ViewActions.typeText(inputText)
        )
        onView(withId(R.id.activityChangeTextBtn)).perform(ViewActions.click())
        onView(withId(R.id.show_text_view)).check(matches(withText(inputText)))
    }

    // Test 4: Without entering anything and press Change Text button and test the string (empty/null)
    @Test
    fun emptyInputAndPressChangeTextButton() {
        onView(withId(R.id.changeTextBt)).perform(ViewActions.click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText("")))
    }

    // Test 5: Without entering anything and press Open Activity and Change Text button, and test the string in ShowTextActivity
    @Test
    fun emptyInputAndOpenActivityChangeTextButton() {
        onView(withId(R.id.activityChangeTextBtn)).perform(ViewActions.click())
        onView(withId(R.id.show_text_view)).check(matches(withText("")))
    }

    // Test 6: Enter "abcdef" and press Change Text button, and test the string
    @Test
    fun enterAbcdefAndPressChangeTextButton() {
        val inputText = "abcdef"
        onView(withId(R.id.editTextUserInput)).perform(
            ViewActions.clearText(),
            ViewActions.typeText(inputText)
        )
        onView(withId(R.id.changeTextBt)).perform(ViewActions.click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText(inputText)))
    }

    // Test 7: Enter "abcdef" and press Open Activity and Change Text button, and test the string in ShowTextActivity
    @Test
    fun enterAbcdefAndOpenActivityChangeTextButton() {
        val inputText = "abcdef"
        onView(withId(R.id.editTextUserInput)).perform(
            ViewActions.clearText(),
            ViewActions.typeText(inputText)
        )
        onView(withId(R.id.activityChangeTextBtn)).perform(ViewActions.click())
        onView(withId(R.id.show_text_view)).check(matches(withText(inputText)))
    }

    // Test 8: Validate correct strings in the TextView in the ShowTextActivity
    // This test needs to be run in ShowTextActivity context
    @Test
    fun validateTextViewInShowTextActivity() {
        // First go to ShowTextActivity
        onView(withId(R.id.activityChangeTextBtn)).perform(ViewActions.click())
        // Check the default/empty state
        onView(withId(R.id.show_text_view)).check(matches(withText("")))
    }
}