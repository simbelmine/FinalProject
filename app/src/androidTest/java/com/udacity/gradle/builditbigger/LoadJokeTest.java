package com.udacity.gradle.builditbigger;

import android.content.ComponentName;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.jokeandroidlib.JokeActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.android.jokeandroidlib.AndroidLibConstants.JOKE;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class LoadJokeTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void jokeNotEmptyTest() throws InterruptedException {
        onView(withId(R.id.tell_joke_btn)).perform(click());

        // Check the action is correctly performed
        TimeUnit.SECONDS.sleep(5);
        onView(withId(R.id.joke_container))
                .check(matches(not(withText(""))));
    }
}
