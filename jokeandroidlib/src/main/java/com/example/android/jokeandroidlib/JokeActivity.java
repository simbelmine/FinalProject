package com.example.android.jokeandroidlib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.android.jokeandroidlib.AndroidLibConstants.JOKE;

/**
 * Created by Sve on 8/19/17.
 */

public class JokeActivity extends AppCompatActivity {
    private TextView jokeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_layout);

        Bundle bundle = getIntent().getExtras();
        String joke = bundle.getString(JOKE, "");

        jokeView = (TextView) findViewById(R.id.joke_container);
        // Set joke from Google Endpoint
        jokeView.setText(joke);

        // Set joke from JokeTellerLib
        //jokeView.setText(new JokeTeller().tellJoke());
    }
}
