package com.example.android.jokeandroidlib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.JokeTeller;

/**
 * Created by Sve on 8/19/17.
 */

public class JokeActivity extends AppCompatActivity {
    private TextView jokeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_layout);

        jokeView = (TextView) findViewById(R.id.joke_container);
        jokeView.setText(new JokeTeller().tellJoke());
    }
}
