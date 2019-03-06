package com.example.mobile_project.Controller;

import com.example.mobile_project.Model.AnimeInTopList;
import com.example.mobile_project.R;
import com.example.mobile_project.View.AnimeDescActivity;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder>
{
    public interface OnItemClickListener {
        void onItemClick(AnimeInTopList item);
    }

    public interface OnBottomReachedListener {

        void onBottomReached(int position);
    }

    public void addItemsInList()
    {

    }

    private List<AnimeInTopList> values;
    private final OnItemClickListener listener;
    private final OnBottomReachedListener scrollListener;
    OnBottomReachedListener onBottomReachedListener;
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

        public void bind(final AnimeInTopList item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                   listener.onItemClick(item);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TopAdapter(List<AnimeInTopList> myDataset, OnItemClickListener listener, OnBottomReachedListener scrollListener, Context context) {
        values = myDataset;
        this.listener = listener;
        this.scrollListener = scrollListener;
        this.context = context;
    }



    // Create new views (invoked by the layout manager)
    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.item_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        AnimeInTopList currentTopAnimeInTopListListStruct = values.get(position);
        final String name = currentTopAnimeInTopListListStruct.getTitle();
        holder.bind(values.get(position), listener);

        if (position == values.size() - 1){
            this.scrollListener.onBottomReached(position);
        }

        holder.txtHeader.setText(name);
        Picasso.get()
                .load(currentTopAnimeInTopListListStruct.getImage_url())
                .resize(110, 180)
                .into(holder.loadedImage);
        holder.txtFooter.setText("Rank: " + currentTopAnimeInTopListListStruct.getRank());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(values!=null) {
            return values.size();
        }
        return 0;
    }
}
