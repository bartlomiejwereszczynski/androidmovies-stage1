package com.example.werek.themoviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.werek.themoviedb.model.Movie;
import com.squareup.picasso.Picasso;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = DetailsActivity.class.getName();
    protected Movie mMovie;
    @BindView(R.id.iv_poster)
    ImageView mPoster;
    @BindView(R.id.iv_backdrop)
    ImageView mBackdrop;
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_synopsis)
    TextView mSynopsis;
    @BindView(R.id.tv_release_date)
    TextView mReleaseDate;
    @BindView(R.id.tv_user_rating)
    TextView mRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(MainActivity.MOVIE_EXTRA)) {
            Log.d(TAG, "got movie in incoming intent");
            mMovie = intent.getParcelableExtra(MainActivity.MOVIE_EXTRA);
        }

        if (mMovie != null) {
            Log.d(TAG, "Movie details: " + mMovie.toString());
            loadMovie(mMovie);
        }
    }

    private void loadMovie(Movie movie) {
        mTitle.setText(movie.getOriginalTitle());
        mSynopsis.setText(movie.getOverview());
        mReleaseDate.setText(movie.getReleaseDate());
        mRating.setText(String.valueOf(movie.getVoteAverage()));
        URL backdrop = movie.getBackdropUrl();
        URL poster = movie.getPosterUrl();
        if (poster != null) {
            Picasso.with(this)
                    .load(poster.toString())
                    .into(mPoster);
        }
        if (backdrop != null) {
            Picasso.with(this)
                    .load(backdrop.toString())
                    .into(mBackdrop);
        }
        setTitle(movie.getTitle());
    }
}
