package com.example.werek.themoviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.werek.themoviedb.model.Movie;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = DetailsActivity.class.getName();
    protected Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(MainActivity.MOVIE_EXTRA)) {
            Log.d(TAG, "got movie in incoming intent");
            mMovie = (Movie) intent.getParcelableExtra(MainActivity.MOVIE_EXTRA);
        }

        if (mMovie != null) {
            Log.d(TAG, "Movie details: " + mMovie.toString());
        }
    }
}
