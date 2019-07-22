package com.example.flickster2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flickster2.DetailActivity;
import com.example.flickster2.R;
import com.example.flickster2.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MoviesAdapter extends  RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    Context context;
    List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        // binding data into view holder
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;

        public ViewHolder(View itemView)
        {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie)
        {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
          Glide.with(context).load(movie.getPosterPath()).into(ivPoster);

          //add a click listerner on the whole row
            // navigate to detail activity
         // tvTitle.setOnClickListener(new View.OnClickListener()
            container.setOnClickListener(new View.OnClickListener(){
              @Override
              public void onClick(View view) {
                //  Toast.makeText(context, movie.getTitle(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, DetailActivity.class);
               // i.putExtra("title",movie.getTitle());
                i.putExtra("movie", Parcels.wrap(movie));
                context.startActivity(i);
              }
          });
        }
    }
}
