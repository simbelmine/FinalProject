package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.jokeandroidlib.JokeActivity;
import com.example.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import static com.example.android.jokeandroidlib.AndroidLibConstants.JOKE;
import static com.udacity.gradle.builditbigger.AppConstants.ROOT_URL;

/**
 * Created by Sve on 8/20/17.
 */

public class LoadJokeEndpointAsyncTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private MyApi myApiService = null;
    private ProgressBar progressBar;

    public LoadJokeEndpointAsyncTask(Context context) {
        this.context = context;
        progressBar = (ProgressBar) ((Activity) context).findViewById(R.id.progress_bar);
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(ROOT_URL);

            myApiService = builder.build();
        }

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            ((Activity) context).runOnUiThread(new Runnable() {
                public void run() {
                   progressBar.setVisibility(View.GONE);
                }
            });
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        progressBar.setVisibility(View.GONE);

        Bundle bundle = new Bundle();
        bundle.putString(JOKE, joke);

        launchJokeActivity(bundle);
    }

    private void launchJokeActivity(Bundle bundle) {
        Intent jokeIntent = new Intent(context, JokeActivity.class);
        if(bundle != null) {
            jokeIntent.putExtras(bundle);
        }
        context.startActivity(jokeIntent);
    }
}
