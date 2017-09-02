package com.udacity.gradle.builditbigger;

import android.content.ComponentName;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.jokeandroidlib.activity.JokeActivity;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.android.jokeandroidlib.AndroidLibConstants.JOKE;

@RunWith(AndroidJUnit4.class)
public class LoadCustomIntentTest {
    private static final String TEST_INPUT = "test_joke";

    @Test
    public void loadCustomIntentTest() throws InterruptedException {
        Intent intent = new Intent();
        intent.putExtra(JOKE, TEST_INPUT);

        String packageName = InstrumentationRegistry.getTargetContext().getPackageName();
        ComponentName componentName = new ComponentName(packageName,
                JokeActivity.class.getName());
        intent.setComponent(componentName);

        Intents.init();
        // start activity from test app
        InstrumentationRegistry.getContext().startActivity(intent);

        // Check the intent is handled by the app
        Matcher<Intent> expectedIntent = hasComponent(componentName);
        intended(expectedIntent);
        Intents.release();

        TimeUnit.SECONDS.sleep(1);
        onView(withId(R.id.joke_container))
                .check(matches(withText(TEST_INPUT)));
    }
}
