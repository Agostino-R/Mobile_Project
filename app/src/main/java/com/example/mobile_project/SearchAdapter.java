package com.example.mobile_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobile_project.Model.AnimeInSearchList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(AnimeInSearchList item);
    }

    public interface OnBottomReachedListener {

        void onBottomReached(int position);
    }

    private List<AnimeInSearchList> values;
    private final SearchAdapter.OnItemClickListener listener;
    private final SearchAdapter.OnBottomReachedListener scrollListener;
    SearchAdapter.OnBottomReachedListener onBottomReachedListener;
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

        public void bind(final AnimeInSearchList item, final SearchAdapter.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SearchAdapter(List<AnimeInSearchList> myDataset, SearchAdapter.OnItemClickListener listener, SearchAdapter.OnBottomReachedListener scrollListener, Context context) {
        values = myDataset;
        this.listener = listener;
        this.scrollListener = scrollListener;
        this.context = context;
    }



    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.item_layout, parent, false);
        SearchAdapter.ViewHolder vh = new SearchAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, final int position) {
        AnimeInSearchList currentAnimeInSearchList = values.get(position);
        holder.bind(values.get(position), listener);

        if (position == values.size() - 1){
            this.scrollListener.onBottomReached(position);
        }

        holder.txtHeader.setText(currentAnimeInSearchList.getTitle());
        Picasso.get()
                .load(currentAnimeInSearchList.getImage_url())
                .resize(110, 180)
                .into(holder.loadedImage);
        holder.txtFooter.setText("Rank: " + currentAnimeInSearchList.getScore());
    }

    @Override
    public int getItemCount() {
        if(values!=null) {
            return values.size();
        }
        return 0;
    }
}
