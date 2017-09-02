package com.example.android.jokeandroidlib.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.jokeandroidlib.R;

import static com.example.android.jokeandroidlib.AndroidLibConstants.JOKE;

public class JokeActivity extends AppCompatActivity {
    private TextView jokeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_layout);

        Bundle bundle = getIntent().getExtras();
        String joke = "";
        if (bundle != null && getIntent().hasExtra(JOKE)) {
            joke = bundle.getString(JOKE);
        }

        jokeView = (TextView) findViewById(R.id.joke_container);
        // Set joke from Google Endpoint
        jokeView.setText(joke);
    }
}
