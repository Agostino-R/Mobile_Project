package com.example.mobile_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobile_project.Model.AnimeInToWatchList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ToWatchAdapter extends RecyclerView.Adapter<ToWatchAdapter.ViewHolder>
{
    public interface OnItemClickListener {
        void onItemClick(AnimeInToWatchList item);
    }

    private ArrayList<AnimeInToWatchList> values;
    private final ToWatchAdapter.OnItemClickListener listener;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView txtHeader;
        private TextView txtFooter;
        private ImageView loadedImage;
        private View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            loadedImage = (ImageView) v.findViewById(R.id.anime_image);
        }

        public void bind(final AnimeInToWatchList item, final ToWatchAdapter.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public ToWatchAdapter(ArrayList<AnimeInToWatchList> myDataset, OnItemClickListener listener, Context context) {
        values = myDataset;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public ToWatchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.item_layout, parent, false);
        ToWatchAdapter.ViewHolder vh = new ToWatchAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ToWatchAdapter.ViewHolder holder, final int position) {
        AnimeInToWatchList currentAnimeInToWatchList = values.get(position);
        final String name = currentAnimeInToWatchList.getTitle();
        holder.bind(values.get(position), listener);

        holder.txtHeader.setText(name);
        Picasso.get()
                .load(currentAnimeInToWatchList.getImage_url())
                .resize(110, 180)
                .into(holder.loadedImage);
        holder.txtFooter.setText("Rank: " + currentAnimeInToWatchList.getScore());
    }

    @Override
    public int getItemCount() {
        if (values != null) {
            return values.size();
        }
        return 0;
    }
}
