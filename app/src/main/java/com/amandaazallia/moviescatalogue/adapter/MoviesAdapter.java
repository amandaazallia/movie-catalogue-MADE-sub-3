package com.amandaazallia.moviescatalogue.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amandaazallia.moviescatalogue.R;
import com.amandaazallia.moviescatalogue.activity.MoviesDetailActivity;
import com.amandaazallia.moviescatalogue.model.Movies;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private ArrayList<Movies> mData = new ArrayList<>();
    public void setData(ArrayList<Movies> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_movies, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPhoto;
        TextView textViewTitle, textViewDateReleased,
                textViewVoteAverage, textViewVoteCount;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_item_title);
            textViewDateReleased = itemView.findViewById(R.id.tv_item_dateReleased);
            textViewVoteAverage = itemView.findViewById(R.id.tv_item_voteAverege);
            textViewVoteCount = itemView.findViewById(R.id.tv_item_voteCount);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);


            itemView.setOnClickListener(this);
        }
        void bind(Movies movies) {
            String vote_average = Double.toString(movies.getVote_average());
            String url_image = "https://image.tmdb.org/t/p/w185" + movies.getPhoto();


            textViewTitle.setText(movies.getTitle());
            textViewDateReleased.setText(movies.getRelease_date());
            textViewVoteAverage.setText(vote_average);
            textViewVoteCount.setText(movies.getVote_count());


            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPhoto);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movies movie = mData.get(position);
//
            movie.setTitle(movie.getTitle());
            movie.setOverview(movie.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), MoviesDetailActivity.class);
            moveWithObjectIntent.putExtra(MoviesDetailActivity.EXTRA_MOVIE, movie);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }


}
