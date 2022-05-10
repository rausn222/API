package com.example.demo;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;


import com.example.demo.data.Datas;
import com.example.demo.model.DataModel;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends TestCase {
    Context instrumentationContext;
    @Before
    public void setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().getContext();
    }
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);
    int ITEM = 10;
    Datas datas = new Datas();
    DataModel dm = datas.fakeData.get(ITEM);
    @Test
    public void recycleTest(){
        onView(withId(R.id.rvNews)).check(matches(isDisplayed()));
    }
    @Test
    public void testRecyclerViewClick() {
        onView(withId(R.id.rvNews)).perform(actionOnItemAtPosition(ITEM,click()));
        onView(withId(R.id.textView_title)).check(matches(withText(dm.getTitle())));
    }
    @Test
    public void testRecyclerViewBack() {
        onView(withId(R.id.rvNews)).perform(actionOnItemAtPosition(ITEM,click()));
        onView(withId(R.id.textView_title)).check(matches(withText(dm.getTitle())));
        pressBack();
        onView(withId(R.id.rvNews)).check(matches(isDisplayed()));
    }

}