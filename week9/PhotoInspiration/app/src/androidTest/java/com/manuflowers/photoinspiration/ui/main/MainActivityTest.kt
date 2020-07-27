package com.manuflowers.photoinspiration.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.manuflowers.photoinspiration.R
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val viewGroup = onView(
            withId(R.id.mainBottomNavigationView)
        )
        viewGroup.check(matches(not(viewGroup)))
    }

    @Test
    fun isUserNameEditTextDisplayed() {
        val view = onView(
            withId(R.id.userNameEditText)
        )
        view.check(matches(
            isDisplayed()
        ))
    }

    @Test
    fun isPasswordUserNameEditTextDisplayed() {
        val view = onView(
            withId(R.id.passwordEditText)
        )
        view.check(matches(isDisplayed()))
    }
}
