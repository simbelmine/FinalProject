package com.udacity.gradle.builditbigger.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.BuildConfig;
import com.udacity.gradle.builditbigger.LoadJokeEndpointAsyncTask;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.utils.Constants;


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
        if(Constants.type.FREE == Constants.type && mInterstitialAd.isLoaded()) {
            showInterstitialAd();
        } else {
            new LoadJokeEndpointAsyncTask(this).execute();
        }
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
