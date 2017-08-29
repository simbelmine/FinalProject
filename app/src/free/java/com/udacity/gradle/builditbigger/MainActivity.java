package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.JokeTeller;
import com.example.android.jokeandroidlib.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMobileAd();
        initInterstitialAd();
        loadInterstitialAd();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // onClick method
    public void tellJoke(View view) {
        //showJokeToast();
        // Start Joke Android Library Activity to show the joke.
        //launchJokeActivity();

        if(Constants.type.FREE == Constants.type && mInterstitialAd.isLoaded()) {
            showInterstitialAd();
        } else {
            new LoadJokeEndpointAsyncTask(this).execute();
        }
    }

    private void showJokeToast() {
        Toast toast = Toast.makeText(this, new JokeTeller().tellJoke(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void launchJokeActivity() {
        Intent jokeIntent = new Intent(this, JokeActivity.class);
        startActivity(jokeIntent);
    }

    private void initMobileAd() {
        MobileAds.initialize(this, BuildConfig.AD_MOB_APP_ID);
    }

    private void initInterstitialAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(BuildConfig.AD_UNIT_ID);
        mInterstitialAd.setAdListener(adListener);
    }

    private void loadInterstitialAd() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    private void showInterstitialAd() {
        mInterstitialAd.show();
    }

    private AdListener adListener = new AdListener() {
        @Override
        public void onAdClosed() {
            new LoadJokeEndpointAsyncTask(MainActivity.this).execute();
        }
    };
}
