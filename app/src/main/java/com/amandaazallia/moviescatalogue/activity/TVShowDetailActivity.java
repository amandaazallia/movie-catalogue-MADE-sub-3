package com.amandaazallia.moviescatalogue.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amandaazallia.moviescatalogue.R;
import com.amandaazallia.moviescatalogue.model.TVShow;
import com.bumptech.glide.Glide;

public class TVShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "extra_movie";

    TextView tvName, tvVoteAverage, tvVoteCount, tvLanguage, tvOverview;
    ImageView imagePhoto;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);

        tvName = findViewById(R.id.tv_item_name);
        tvVoteAverage = findViewById(R.id.tv_item_voteAverege);
        tvVoteCount = findViewById(R.id.tv_item_voteCount);
        tvOverview = findViewById(R.id.tv_item_overview);
        tvLanguage = findViewById(R.id.tv_item_language);
        imagePhoto = findViewById(R.id.img_item_photo);
        progressBar = findViewById(R.id.progressBarShowDetail);

        progressBar.setVisibility(View.VISIBLE);

        final Handler handler = new Handler();

        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(5000);
                }
                catch (Exception e) { }

                handler.post(new Runnable() {
                    public void run() {
                        TVShow tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);

                        String vote_average = Double.toString(tvShow.getVote_average());
                        String url_image = "https://image.tmdb.org/t/p/w185" + tvShow.getPoster_path();

                        tvName.setText(tvShow.getName());
                        tvVoteAverage.setText(vote_average);
                        tvVoteCount.setText(tvShow.getVote_count());
                        tvOverview.setText(tvShow.getOverview());
                        tvLanguage.setText(tvShow.getOriginal_language()) ;

                        Glide.with(TVShowDetailActivity.this)
                                .load(url_image)
                                .placeholder(R.color.colorAccent)
                                .dontAnimate()
                                .into(imagePhoto);

                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }
}
