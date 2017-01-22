package com.example.werek.themoviedb;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.werek.themoviedb.model.Movie;
import com.example.werek.themoviedb.model.MoviesList;
import com.example.werek.themoviedb.util.MovieDbApi;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieDetailsListener {
    public static final String MOVIE_EXTRA = BuildConfig.APPLICATION_ID + "movieItem";
    private MovieAdapter mMovieAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_poster_list);
        mMovieAdapter = new MovieAdapter(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        mRecyclerView.setLayoutManager(layoutManager);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMovieAdapter);

        new LoadMoviesTask().execute();
    }

    @Override
    public void onMovieDetails(Movie movie) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(MOVIE_EXTRA, movie);
        startActivity(intent);
    }

    class LoadMoviesTask extends AsyncTask<Void, Void, MoviesList> {
        private final String TAG = LoadMoviesTask.class.getName();

        /**
         * Runs on the UI thread before {@link #doInBackground}.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute() {
            mMovieAdapter.setMovieList(null);
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected MoviesList doInBackground(Void... params) {
            MovieDbApi api = new MovieDbApi();
            api.setApiKey(BuildConfig.MOVIE_DB_API_KEY);
            String language = Locale.getDefault().toString();
            Log.d(TAG, "language used for TMDB: " + language);
            api.setLanguage(language);
            return api.popular();
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param moviesList The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(MoviesList moviesList) {
            if (moviesList != null) {
                Log.d(TAG, "got response with " + moviesList.getResults().size() + " movies");
                mMovieAdapter.setMovieList(moviesList.getResults());
            } else {
                Log.d(TAG, "got empty result response");
            }
        }
    }
}
