package com.example.mobile_project;

import com.example.mobile_project.Model.AnimeInTopList;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

    private List<AnimeInTopList> values;
    private final OnItemClickListener listener;
    private final OnBottomReachedListener scrollListener;
    private Context context;
    private GestureDetector gestureDetector;

    public class ViewHolder extends RecyclerView.ViewHolder {
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
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public TopAdapter(List<AnimeInTopList> myDataset, OnItemClickListener listener, OnBottomReachedListener scrollListener, Context context) {
        values = myDataset;
        this.listener = listener;
        this.scrollListener = scrollListener;
        this.context = context;
        this.gestureDetector = new GestureDetector(context, new SingleTapConfirm());
    }



    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AnimeInTopList currentTopAnimeInTopListListStruct = values.get(position);
        final String name = currentTopAnimeInTopListListStruct.getTitle();
        holder.bind(values.get(position), listener);

        if (position == values.size() - 1){
            this.scrollListener.onBottomReached(position);
        }

        holder.txtHeader.setText(name);
        Picasso.get()
                .load(currentTopAnimeInTopListListStruct.getImage_url())
                .resize(120, 190)
                .into(holder.loadedImage);
        holder.txtFooter.setText("Rank: " + currentTopAnimeInTopListListStruct.getRank());
    }

    @Override
    public int getItemCount() {
        if(values!=null) {
            return values.size();
        }
        return 0;
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }
}
